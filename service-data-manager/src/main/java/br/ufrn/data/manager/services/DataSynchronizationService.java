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
    public OpenData syncCkan() {
        return syncData(openDataAccessClient::getCkanData, messageQueueProperties.getCkanQueueName());
    }

    @Override
    public OpenData syncDkan() {
        return syncData(openDataAccessClient::getDkanData, messageQueueProperties.getDkanQueueName());
    }

    @Override
    public OpenData syncSocrata() {
        return syncData(openDataAccessClient::getSocrataData, messageQueueProperties.getSocrataQueueName());
    }

    private OpenData syncData(Supplier<OpenData> dataSupplier, String queueName) {
        logger.info("Starting data synchronization for: {}", dataSupplier.get());

        OpenData accessDataResponse = dataSupplier.get();
        logger.info("Data fetched successfully for: {}", dataSupplier.get());

        ResponseEntity<Void> cacheResponse = cacheClient.createCache(accessDataResponse);
        if (!cacheResponse.getStatusCode().is2xxSuccessful()) {
            logger.error("Failed to create cache for: {}", dataSupplier.get());
            throw new CacheCreationException("Failed to create cache for: " + dataSupplier);
        }

        logger.info("Data cached successfully for: {}", dataSupplier.get());

        messageRepository.send(queueName, accessDataResponse.getData());
        logger.info("Data sent to queue: {}", queueName);

        return accessDataResponse;
    }
}