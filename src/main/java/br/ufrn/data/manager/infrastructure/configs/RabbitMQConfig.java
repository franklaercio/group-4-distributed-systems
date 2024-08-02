package br.ufrn.data.manager.infrastructure.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue ckanQueue() {
        return new Queue(rabbitMQProperties.getCkanQueueName());
    }

    @Bean
    public Queue dkanQueue() {
        return new Queue(rabbitMQProperties.getDkanQueueName());
    }

    @Bean
    public Queue socrataQueue() {
        return new Queue(rabbitMQProperties.getSocrataQueueName());
    }
}

