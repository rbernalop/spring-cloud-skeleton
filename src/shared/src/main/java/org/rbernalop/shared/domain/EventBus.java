package java.org.rbernalop.shared.domain;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}