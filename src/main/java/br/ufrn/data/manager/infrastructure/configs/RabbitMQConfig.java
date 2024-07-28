package br.ufrn.data.manager.infrastructure.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(rabbitMQProperties.getExchangeName());
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

    @Bean
    public Binding ckanBinding(Queue ckanQueue, DirectExchange exchange) {
        return BindingBuilder.bind(ckanQueue).to(exchange).with(rabbitMQProperties.getCkanRoutingKey());
    }

    @Bean
    public Binding dkanBinding(Queue dkanQueue, DirectExchange exchange) {
        return BindingBuilder.bind(dkanQueue).to(exchange).with(rabbitMQProperties.getDkanRoutingKey());
    }

    @Bean
    public Binding socrataBinding(Queue socrataQueue, DirectExchange exchange) {
        return BindingBuilder.bind(socrataQueue).to(exchange).with(rabbitMQProperties.getSocrataRoutingKey());
    }
}

