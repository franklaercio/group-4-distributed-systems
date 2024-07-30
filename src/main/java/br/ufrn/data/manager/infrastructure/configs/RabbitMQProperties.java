package br.ufrn.data.manager.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQProperties {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.queue.ckan}")
    private String ckanQueueName;

    @Value("${rabbitmq.queue.dkan}")
    private String dkanQueueName;

    @Value("${rabbitmq.queue.socrata}")
    private String socrataQueueName;

    @Value("${rabbitmq.queue.teste}")
    private String testeQueueName;

    @Value("${rabbitmq.routingkey.ckan}")
    private String ckanRoutingKey;

    @Value("${rabbitmq.routingkey.dkan}")
    private String dkanRoutingKey;

    @Value("${rabbitmq.routingkey.socrata}")
    private String socrataRoutingKey;

    @Value("${rabbitmq.routingkey.teste}")
    private String testeRoutingKey;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

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

    public String getTesteQueueName() {
        return testeQueueName;
    }

    public void setTesteQueueName(String testeQueueName) {
        this.testeQueueName = testeQueueName;
    }

    public String getCkanRoutingKey() {
        return ckanRoutingKey;
    }

    public void setCkanRoutingKey(String ckanRoutingKey) {
        this.ckanRoutingKey = ckanRoutingKey;
    }

    public String getDkanRoutingKey() {
        return dkanRoutingKey;
    }

    public void setDkanRoutingKey(String dkanRoutingKey) {
        this.dkanRoutingKey = dkanRoutingKey;
    }

    public String getSocrataRoutingKey() {
        return socrataRoutingKey;
    }

    public void setSocrataRoutingKey(String socrataRoutingKey) {
        this.socrataRoutingKey = socrataRoutingKey;
    }

    public String getTesteRoutingKey() {
        return testeRoutingKey;
    }

    public void setTesteRoutingKey(String testeRoutingKey) {
        this.testeRoutingKey = testeRoutingKey;
    }
}
