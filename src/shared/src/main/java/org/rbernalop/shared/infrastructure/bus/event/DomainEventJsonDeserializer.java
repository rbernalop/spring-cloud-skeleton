package org.rbernalop.shared.infrastructure.bus.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.bus.event.DomainEvent;
import org.rbernalop.shared.domain.Service;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Service
@AllArgsConstructor
public final class DomainEventJsonDeserializer {
    private final DomainEventsInformation information;

    private static HashMap<String, Serializable> jsonDecode(String body) {
        try {
            return new ObjectMapper().readValue(body, HashMap.class);
        } catch (IOException e) {
            return null;
        }
    }

    public DomainEvent deserialize(String body) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        HashMap<String, Serializable> eventData        = DomainEventJsonDeserializer.jsonDecode(body);
        HashMap<String, Serializable> data             = (HashMap<String, Serializable>) eventData.get("data");
        HashMap<String, Serializable> attributes       = (HashMap<String, Serializable>) data.get("attributes");
        Class<? extends DomainEvent>  domainEventClass = information.forName((String) data.get("type"));

        DomainEvent nullInstance = domainEventClass.getConstructor().newInstance();

        Method fromPrimitivesMethod = domainEventClass.getMethod(
                "fromPrimitives",
                String.class,
                HashMap.class,
                String.class,
                String.class
        );

        Object domainEvent = fromPrimitivesMethod.invoke(
            nullInstance,
            (String) attributes.get("id"),
            attributes,
            (String) data.get("id"),
            (String) data.get("occurred_on")
        );

        return (DomainEvent) domainEvent;
    }
}