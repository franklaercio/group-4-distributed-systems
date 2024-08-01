package br.ufrn.data.manager.services;

import br.ufrn.data.manager.domain.OpenDataEntity;
import br.ufrn.data.manager.domain.ResourceEnum;
import br.ufrn.data.manager.domain.exceptions.CacheCreationException;
import br.ufrn.data.manager.infrastructure.openfeign.CacheClient;
import br.ufrn.data.manager.infrastructure.openfeign.OpenAccessClient;
import br.ufrn.data.manager.repositories.MessageRepository;
import br.ufrn.data.manager.repositories.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataSyncService implements ScheduleRepository {

    private static final Logger logger = LoggerFactory.getLogger(DataSyncService.class);

    private final OpenAccessClient openAccessClient;
    private final TaxService taxService;
    private final CacheClient cacheClient;
    private final MessageRepository messageRepository;

    @Value("${internal.token")
    private String token;

    public DataSyncService(OpenAccessClient openAccessClient, TaxService taxService, CacheClient cacheClient, MessageRepository messageRepository) {
        this.openAccessClient = openAccessClient;
        this.taxService = taxService;
        this.cacheClient = cacheClient;
        this.messageRepository = messageRepository;
    }

    @Override
    public void sync(String routingKey, String datasource) {
        try {
            OpenDataEntity openData = taxService.getRandomTax(datasource);
            logger.info("Data fetched from {} successfully: {}", datasource, openData);
            logger.info("Token: {}", token);

            ResponseEntity<Void> cacheResponse = cacheClient.createCache(token, openData);
            if (!cacheResponse.getStatusCode().is2xxSuccessful()) {
                logger.error("Failed to create cache from {}: {}", datasource, cacheResponse);
                throw new CacheCreationException("Failed to create cache for: " + datasource);
            }

            messageRepository.send(routingKey, openData.getData());
            logger.info("Data from {} sent to message queue with routing key {}", datasource, routingKey);
        } catch (Exception ex) {
            logger.error("An error occurred while syncing data from {}", datasource);
            throw new RuntimeException("Data sync failed for datasource: " + datasource, ex);
        }
    }
}
