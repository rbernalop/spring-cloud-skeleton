package java.org.rbernalop.shared.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.org.rbernalop.shared.domain.Service;
import java.org.rbernalop.shared.domain.DomainEvent;
import java.org.rbernalop.shared.domain.EventBus;
import java.util.List;

@AllArgsConstructor
@Service
public class SpringApplicationEventBus implements EventBus {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(publisher::publishEvent);
    }

    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    }
}
