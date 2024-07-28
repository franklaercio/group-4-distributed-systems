package br.ufrn.data.manager.services;

import br.ufrn.data.manager.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageRepository {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    public MessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(String queue, Object message) {
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        logger.info("Message sent to queue {}: {}", queue, message);
    }
}
