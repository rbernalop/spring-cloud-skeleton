package java.org.rbernalop.shared.application;

import main.java.org.rbernalop.shared.domain.DomainEvent;

public interface DomainEventSubscriber<EventType extends DomainEvent> {
    Class<EventType> subscribedTo();

    void consume(EventType event);
}