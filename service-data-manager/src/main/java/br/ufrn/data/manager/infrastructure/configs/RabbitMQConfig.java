package br.ufrn.data.manager.infrastructure.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final MessageQueueProperties messageQueueProperties;

    public RabbitMQConfig(MessageQueueProperties messageQueueProperties) {
        this.messageQueueProperties = messageQueueProperties;
    }

    @Bean
    public Queue queue() {
        return new Queue(messageQueueProperties.getCkanQueueName(), true);
    }

    @Bean
    public Queue dkanQueue() {
        return new Queue(messageQueueProperties.getCkanQueueName(), true);
    }

    @Bean
    public Queue socrataQueue() {
        return new Queue(messageQueueProperties.getCkanQueueName(), true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}

