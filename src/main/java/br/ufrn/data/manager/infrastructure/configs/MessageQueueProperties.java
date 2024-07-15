package br.ufrn.data.manager.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class MessageQueueProperties {

    @Value("${spring.rabbitmq.opendata.queue}")
    private String openDataQueueName;

    public String getOpenDataQueueName() {
        return openDataQueueName;
    }

    public void setOpenDataQueueName(String openDataQueueName) {
        this.openDataQueueName = openDataQueueName;
    }
}
