package org.rbernalop.shared.domain.bus.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class DomainEvent {
    private String eventId;
    private String eventType;
    private String aggregateId;
    private String occurredOn;

    public DomainEvent(String aggregateId, String eventType) {
        this.eventId     = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.aggregateId = aggregateId;
        this.occurredOn  = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}