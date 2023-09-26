package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberprivilegeschanged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
class BandMemberPrivilegesChangedEventListener {

    private final BandMemberPrivilegesChangedEventProcessor processor;

    @Bean
    SynchronousMessageRouter bandMemberPrivilegesChangedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandMemberPrivilegesChangedEvent.EVENT_TYPE, BandMemberPrivilegesChangedEvent.EVENT_VERSION, BandMemberPrivilegesChangedEvent.class, processor::process)
                .addDefaultEventHandler(message -> log.error("Could not resolve user registered message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
