package br.ufrn.data.manager.infrastructure.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.queue.ckan}")
    private String ckanQueueName;

    @Value("${rabbitmq.queue.dkan}")
    private String dkanQueueName;

    @Value("${rabbitmq.queue.socrata}")
    private String socrataQueueName;

    @Value("${rabbitmq.routingkey.ckan}")
    private String ckanRoutingKey;

    @Value("${rabbitmq.routingkey.dkan}")
    private String dkanRoutingKey;

    @Value("${rabbitmq.routingkey.socrata}")
    private String socrataRoutingKey;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue ckanQueue() {
        return new Queue(ckanQueueName);
    }

    @Bean
    public Queue dkanQueue() {
        return new Queue(dkanQueueName);
    }

    @Bean
    public Queue socrataQueue() {
        return new Queue(socrataQueueName);
    }

    @Bean
    public Binding ckanBinding(Queue ckanQueue, DirectExchange exchange) {
        return BindingBuilder.bind(ckanQueue).to(exchange).with(ckanRoutingKey);
    }

    @Bean
    public Binding dkanBinding(Queue dkanQueue, DirectExchange exchange) {
        return BindingBuilder.bind(dkanQueue).to(exchange).with(dkanRoutingKey);
    }

    @Bean
    public Binding socrataBinding(Queue socrataQueue, DirectExchange exchange) {
        return BindingBuilder.bind(socrataQueue).to(exchange).with(socrataRoutingKey);
    }
}

