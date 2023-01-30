package org.rbernalop.shared.infrastructure.bus.event.kafka;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.bus.event.DomainEvent;
import org.rbernalop.shared.domain.bus.event.EventBus;

import java.util.List;

@AllArgsConstructor
public class KafkaEventBus implements EventBus {
    private final KafkaPublisher publisher;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
        try {
            this.publisher.publish(domainEvent);
        } catch (Exception error) {
            throw new RuntimeException("Error publishing event", error);
        }
    }
}
