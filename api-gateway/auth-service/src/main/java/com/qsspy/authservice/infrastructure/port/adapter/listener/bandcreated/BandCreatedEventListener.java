package com.qsspy.authservice.infrastructure.port.adapter.listener.bandcreated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
class BandCreatedEventListener {

    private final BandCreatedEventProcessor processor;

    @Bean
    SynchronousMessageRouter bandCreatedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandCreatedEvent.EVENT_TYPE, BandCreatedEvent.EVENT_VERSION, BandCreatedEvent.class, processor::process)
                .addDefaultEventHandler(message -> log.error("Could not resolve user registered message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
