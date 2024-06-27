package br.ufrn.data.manager.services;

import br.ufrn.data.manager.infrastructure.configs.MessageQueueProperties;
import br.ufrn.data.manager.infrastructure.openfeign.CacheClient;
import br.ufrn.data.manager.infrastructure.openfeign.OpenDataAccessClient;
import br.ufrn.data.manager.model.OpenData;
import br.ufrn.data.manager.model.exceptions.CacheCreationException;
import br.ufrn.data.manager.repositories.MessageRepository;
import br.ufrn.data.manager.repositories.OpenDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class DataSynchronizationService implements OpenDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(DataSynchronizationService.class);

    private final OpenDataAccessClient openDataAccessClient;

    private final CacheClient cacheClient;

    private final MessageRepository messageRepository;

    private final MessageQueueProperties messageQueueProperties;

    public DataSynchronizationService(OpenDataAccessClient openDataAccessClient, CacheClient cacheClient, MessageRepository messageRepository, MessageQueueProperties messageQueueProperties) {
        this.openDataAccessClient = openDataAccessClient;
        this.cacheClient = cacheClient;
        this.messageRepository = messageRepository;
        this.messageQueueProperties = messageQueueProperties;
    }

    @Override
    public OpenData sync(String datasource) {
        logger.info("Starting data synchronization for: {}", datasource);

        OpenData accessDataResponse = this.openDataAccessClient.getData(datasource);
        logger.info("Data fetched successfully for: {}", datasource);

        ResponseEntity<Void> cacheResponse = cacheClient.createCache(accessDataResponse);
        if (!cacheResponse.getStatusCode().is2xxSuccessful()) {
            logger.error("Failed to create cache for: {}", datasource);
            throw new CacheCreationException("Failed to create cache for: " + datasource);
        }

        logger.info("Data cached successfully for: {}", datasource);

        messageRepository.send(queueName, accessDataResponse.getData());
        logger.info("Data sent to queue: {}", queueName);

        return accessDataResponse;
    }
}