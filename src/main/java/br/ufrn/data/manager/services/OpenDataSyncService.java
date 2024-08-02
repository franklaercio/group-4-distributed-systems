package br.ufrn.data.manager.services;

import br.ufrn.data.manager.domain.OpenDataResource;
import br.ufrn.data.manager.domain.exceptions.CacheException;
import br.ufrn.data.manager.infrastructure.openfeign.CacheClient;
import br.ufrn.data.manager.infrastructure.openfeign.OpenDataClient;
import br.ufrn.data.manager.repositories.DataSyncRepository;
import br.ufrn.data.manager.repositories.MessageQueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OpenDataSyncService implements DataSyncRepository {

    private static final Logger logger = LoggerFactory.getLogger(OpenDataSyncService.class);

    @Value("${internal.token}")
    private String token;

    private final OpenDataClient openDataClient;
    private final CacheClient cacheClient;
    private final MessageQueueRepository messageQueueRepository;

    public OpenDataSyncService(OpenDataClient openDataClient, CacheClient cacheClient, MessageQueueRepository messageQueueRepository) {
        this.openDataClient = openDataClient;
        this.cacheClient = cacheClient;
        this.messageQueueRepository = messageQueueRepository;
    }

    @Override
    public void synchronizeData(String routingKey, String datasource) {
        try {
            OpenDataResource openDataResource = openDataClient.getData(datasource);
            logger.info("Data fetched from {} successfully: {}", datasource, openDataResource);

            ResponseEntity<Void> cacheResponse = cacheClient.createCache(token, openDataResource);
            if (!cacheResponse.getStatusCode().is2xxSuccessful()) {
                logger.error("Failed to create cache from {}: {}", datasource, cacheResponse);
                throw new CacheException("Failed to create cache for: " + datasource);
            }
            logger.info("Cache created successfully for {}", datasource);

            messageQueueRepository.sendMessage(routingKey, openDataResource.getId());
            logger.info("Data from {} sent to message queue with routing key {}", datasource, routingKey);
        } catch (Exception ex) {
            logger.error("An error occurred while syncing data from {}", datasource, ex);
            throw new RuntimeException("Data sync failed for datasource: " + datasource, ex);
        }
    }
}