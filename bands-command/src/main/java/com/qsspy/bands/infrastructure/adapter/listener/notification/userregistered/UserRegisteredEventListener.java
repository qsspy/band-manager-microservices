package com.qsspy.bands.infrastructure.adapter.listener.notification.userregistered;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
class UserRegisteredEventListener {

    private final UserRegisteredEventProcessor processor;

    @Bean
    SynchronousMessageRouter userRegisteredEventRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(UserRegisteredEvent.EVENT_TYPE, UserRegisteredEvent.EVENT_VERSION, UserRegisteredEvent.class, processor::process)
                .addDefaultEventHandler(message -> log.error("Could not resolve user registered message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
