package br.ufrn.data.manager.services;

import br.ufrn.data.manager.infrastructure.configs.RabbitMQProperties;
import br.ufrn.data.manager.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageRepository {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    public MessageService(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    @Override
    public void send(String routingKey, Object message) {
        try {
            rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), routingKey, message);
            logger.info("Message sent to queue with routing key {}: {}", routingKey, message);
        } catch (Exception ex) {
            logger.error("Failed to send message to queue with routing key {}: {}", routingKey, message);
            throw new RuntimeException("Failed to send message to queue with routing key " + routingKey, ex);
        }
    }
}
