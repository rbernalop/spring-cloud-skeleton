package org.rbernalop.shared.infrastructure.bus.event.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.rbernalop.shared.domain.bus.event.DomainEvent;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.springframework.amqp.AmqpException;

import java.util.List;

@RequiredArgsConstructor
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
        try {
            String exchangeName = "domain_events";
            this.publisher.publish(domainEvent, exchangeName);
        } catch (AmqpException error) {
            throw new RuntimeException("Error publishing event", error);
        }
    }
}