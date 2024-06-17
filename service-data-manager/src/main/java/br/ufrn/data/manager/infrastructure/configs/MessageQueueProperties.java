package br.ufrn.data.manager.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class MessageQueueProperties {

    @Value("${spring.rabbitmq.ckan.queue}")
    private String ckanQueueName;

    @Value("${spring.rabbitmq.dkan.queue}")
    private String dkanQueueName;

    @Value("${spring.rabbitmq.socrata.queue}")
    private String socrataQueueName;

    public String getCkanQueueName() {
        return ckanQueueName;
    }

    public void setCkanQueueName(String ckanQueueName) {
        this.ckanQueueName = ckanQueueName;
    }

    public String getDkanQueueName() {
        return dkanQueueName;
    }

    public void setDkanQueueName(String dkanQueueName) {
        this.dkanQueueName = dkanQueueName;
    }

    public String getSocrataQueueName() {
        return socrataQueueName;
    }

    public void setSocrataQueueName(String socrataQueueName) {
        this.socrataQueueName = socrataQueueName;
    }
}
