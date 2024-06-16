package br.ufrn.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final OpenDataService openDataService;

    public ScheduledTasks(OpenDataService openDataService) {
        this.openDataService = openDataService;
    }

    @Scheduled(cron = "0 * * * * *") // every minute
    public void callCkan() throws ApiCallException {
        logger.info("Calling CKAN");
        this.openDataService.callApi("/api/v1/ckan");
    }

    @Scheduled(cron = "0 * * * * *") // every minute
    public void callDkan() throws ApiCallException {
        logger.info("Calling DKAN");
        this.openDataService.callApi("/api/v1/dkan");
    }

    @Scheduled(cron = "0 * * * * *") // every minute
    public void callSocrata() throws ApiCallException {
        logger.info("Calling Socrata");
        this.openDataService.callApi("/api/v1/socrata");
    }
}