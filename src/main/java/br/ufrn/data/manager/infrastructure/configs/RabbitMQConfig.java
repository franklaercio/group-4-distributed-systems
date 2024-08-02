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
    public DirectExchange directExchange() {
        return new DirectExchange(rabbitMQProperties.getExchangeName());
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(rabbitMQProperties.getExchangeName()).durable(true).build();
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
    public Queue testeQueue() {
        return new Queue(rabbitMQProperties.getTesteQueueName());
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

    @Bean
    public Binding testeBinding(Queue testeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(testeQueue).to(exchange).with(rabbitMQProperties.getTesteRoutingKey());
    }
}

