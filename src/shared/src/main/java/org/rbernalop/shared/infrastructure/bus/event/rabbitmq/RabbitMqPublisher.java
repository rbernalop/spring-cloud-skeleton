package org.rbernalop.shared.infrastructure.bus.event.rabbitmq;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.infrastructure.bus.event.DomainEventJsonSerializer;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.rbernalop.shared.domain.bus.event.DomainEvent;

@Service
@AllArgsConstructor
public final class RabbitMqPublisher {
    private final RabbitTemplate rabbitTemplate;
    public void publish(DomainEvent domainEvent, String exchangeName) throws AmqpException {
        String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        Message message = new Message(serializedDomainEvent.getBytes(),
            MessagePropertiesBuilder.newInstance()
                .setContentEncoding("utf-8")
                .setContentType("application/json")
                .build()
        );

        rabbitTemplate.send(exchangeName, domainEvent.getEventName(), message);
    }

    public void publish(Message domainEvent, String exchangeName, String routingKey) throws AmqpException {
        rabbitTemplate.send(exchangeName, routingKey, domainEvent);
    }
}
