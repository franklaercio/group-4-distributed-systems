package br.ufrn.data.manager.services;

import br.ufrn.data.manager.repositories.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageRepository {

    private final RabbitTemplate rabbitTemplate;

    public MessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(String queue, Object message) {
        this.rabbitTemplate.convertAndSend(queue, message);
    }
}
