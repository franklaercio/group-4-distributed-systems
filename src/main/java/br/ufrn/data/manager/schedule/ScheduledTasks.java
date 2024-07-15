package br.ufrn.data.manager.schedule;

import br.ufrn.data.manager.services.DataSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final DataSyncService dataSyncService;

    public ScheduledTasks(DataSyncService dataSyncService) {
        this.dataSyncService = dataSyncService;
    }

    @Scheduled(cron = "0 * * * * *") // every minute
    public void callOpenDataApis() {
        syncOpenData("CKAN", "ckan");
        syncOpenData("DKAN", "dkan");
        syncOpenData("Socrata", "socrata");
    }

    private void syncOpenData(String apiName, String apiUrl) {
        logger.info("Starting calling {}", apiName);
        this.dataSyncService.sync(apiUrl);
    }
}