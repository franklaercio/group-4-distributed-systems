package br.ufrn.data.manager;

import br.ufrn.data.manager.services.MessageQueueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Unit tests for MessageQueueService")
@ExtendWith(MockitoExtension.class)
class MessageQueueServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private MessageQueueService messageQueueService;

    @Test
    @DisplayName("Should send message successfully")
    void testSendMessageSuccess() {
        String routingKey = "test.routing.key";
        Object message = "Test Message";

        doNothing().when(rabbitTemplate).convertAndSend(routingKey, message);

        messageQueueService.sendMessage(routingKey, message);

        verify(rabbitTemplate, times(1)).convertAndSend(routingKey, message);
    }

    @Test
    @DisplayName("Should throw exception when message sending fails")
    void testSendMessageFailure() {
        String routingKey = "test.routing.key";
        Object message = "Test Message";
        doThrow(new RuntimeException("RabbitMQ error")).when(rabbitTemplate).convertAndSend(routingKey, message);

        assertThrows(RuntimeException.class, () -> messageQueueService.sendMessage(routingKey, message));

        verify(rabbitTemplate, times(1)).convertAndSend(routingKey, message);
    }
}

