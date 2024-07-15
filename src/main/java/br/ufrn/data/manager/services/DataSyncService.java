package br.ufrn.data.manager.services;

import br.ufrn.data.manager.infrastructure.configs.MessageQueueProperties;
import br.ufrn.data.manager.infrastructure.openfeign.CacheClient;
import br.ufrn.data.manager.infrastructure.openfeign.OpenDataAccessClient;
import br.ufrn.data.manager.domain.OpenDataEntity;
import br.ufrn.data.manager.domain.exceptions.CacheCreationException;
import br.ufrn.data.manager.repositories.MessageRepository;
import br.ufrn.data.manager.repositories.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataSyncService implements ScheduleRepository {

    private static final Logger logger = LoggerFactory.getLogger(DataSyncService.class);

    private final OpenDataAccessClient openDataAccessClient;

    private final CacheClient cacheClient;

    private final MessageRepository messageRepository;

    private final MessageQueueProperties messageQueueProperties;

    public DataSyncService(OpenDataAccessClient openDataAccessClient, CacheClient cacheClient, MessageRepository messageRepository, MessageQueueProperties messageQueueProperties) {
        this.openDataAccessClient = openDataAccessClient;
        this.cacheClient = cacheClient;
        this.messageRepository = messageRepository;
        this.messageQueueProperties = messageQueueProperties;
    }

    @Override
    public void sync(String datasource) {
        OpenDataEntity accessDataResponse = this.openDataAccessClient.getOpenDataByDatasource(datasource);
        logger.info("Data fetched from {} successfully: {}", datasource, accessDataResponse);

        ResponseEntity<Void> cacheResponse = cacheClient.createCache(accessDataResponse);
        if (!cacheResponse.getStatusCode().is2xxSuccessful()) {
            logger.error("Failed to create cache from {}: {}", datasource, cacheResponse);
            throw new CacheCreationException("Failed to create cache for: " + datasource);
        }

        this.messageRepository.send(messageQueueProperties.getOpenDataQueueName(), accessDataResponse.getData());
    }
}