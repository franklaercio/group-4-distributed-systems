package br.ufrn.data.manager.schedule;

import br.ufrn.data.manager.domain.ResourceEnum;
import br.ufrn.data.manager.infrastructure.configs.RabbitMQProperties;
import br.ufrn.data.manager.services.OpenDataSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final OpenDataSyncService openDataSyncService;
    private final RabbitMQProperties rabbitMQProperties;

    public ScheduledTasks(OpenDataSyncService openDataSyncService, RabbitMQProperties rabbitMQProperties) {
        this.openDataSyncService = openDataSyncService;
        this.rabbitMQProperties = rabbitMQProperties;
    }

//    @Scheduled(cron = "0 */10 * * * *") // every 10 minutes
    @Scheduled(cron = "0 * * * * *") // every 10 minutes
    public void callOpenDataApis() {
        syncOpenData(rabbitMQProperties.getCkanRoutingKey(), ResourceEnum.CKAN);
//        syncOpenData(rabbitMQProperties.getDkanRoutingKey(), "dkan");
//        syncOpenData(rabbitMQProperties.getSocrataRoutingKey(), ResourceEnum.SOCRATA);
    }

    private void syncOpenData(String routingKey, ResourceEnum resource) {
        try {
            logger.info("Starting call to {}", resource);
            openDataSyncService.synchronizeData(routingKey, resource.name());
            logger.info("Call to {} completed successfully", resource);
        } catch (Exception ex) {
            logger.error("Error trying to update resource {}. Please contact the responsible group to fix it.", resource);
        }
    }
}