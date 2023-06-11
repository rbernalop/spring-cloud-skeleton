package org.rbernalop.shared.infrastructure.bus.event.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.template")
public class RabbitMQProperties {
    private String exchange;
    private String routingKey;
}
