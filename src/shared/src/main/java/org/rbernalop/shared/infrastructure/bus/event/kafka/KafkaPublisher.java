package org.rbernalop.shared.infrastructure.bus.event.kafka;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.DomainEvent;
import org.rbernalop.shared.infrastructure.bus.event.DomainEventJsonSerializer;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@AllArgsConstructor
public class KafkaPublisher {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void publish(DomainEvent domainEvent) throws AmqpException {
        String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        Message message = new Message(serializedDomainEvent.getBytes(),
            MessagePropertiesBuilder.newInstance()
                .setContentEncoding("utf-8")
                .setContentType("application/json")
                .build()
        );

        kafkaTemplate.send(domainEvent.getEventName(), message);
    }
}
