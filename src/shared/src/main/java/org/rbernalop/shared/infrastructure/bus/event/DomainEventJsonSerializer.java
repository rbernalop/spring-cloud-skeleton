package org.rbernalop.shared.infrastructure.bus.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rbernalop.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class DomainEventJsonSerializer {
    private static String jsonEncode(HashMap<String, Serializable> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static String serialize(DomainEvent domainEvent) {
        HashMap<String, Serializable> attributes = domainEvent.toPrimitives();
        attributes.put("id", domainEvent.getAggregateId());

        return DomainEventJsonSerializer.jsonEncode(new HashMap<>() {{
            put("data", new HashMap<String, Serializable>() {{
                put("id", domainEvent.getEventId());
                put("type", domainEvent.getEventName());
                put("occurred_on", domainEvent.getOccurredOn());
                put("attributes", attributes);
            }});
            put("meta", new HashMap<String, Serializable>());
        }});
    }
}