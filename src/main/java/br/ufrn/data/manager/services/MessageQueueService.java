package br.ufrn.data.manager.services;

import br.ufrn.data.manager.repositories.MessageQueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueService implements MessageQueueRepository {

    private static final Logger logger = LoggerFactory.getLogger(MessageQueueService.class);

    private final RabbitTemplate rabbitTemplate;

    public MessageQueueService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String routingKey, Object message) {
        try {
            rabbitTemplate.convertAndSend(routingKey, message);
            logger.info("Message sent to queue with routing key {}: {}", routingKey, message);
        } catch (Exception ex) {
            logger.error("Failed to send message to queue with routing key {}: {}", routingKey, message);
            throw new RuntimeException("Failed to send message to queue with routing key " + routingKey, ex);
        }
    }
}
