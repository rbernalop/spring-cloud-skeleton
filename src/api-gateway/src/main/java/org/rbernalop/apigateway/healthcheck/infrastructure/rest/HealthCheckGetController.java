package org.rbernalop.apigateway.healthcheck.infrastructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public final class HealthCheckGetController {
    @GetMapping("/health-check")
    public Map<String, String> handle() {
        return Collections.singletonMap("status", "ok");
    }
}
