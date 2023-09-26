package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberadded;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
class BandMemberAddedEventListener {

    private final BandMemberAddedEventProcessor processor;

    @Bean
    SynchronousMessageRouter bandMemberAddedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandMemberAddedEvent.EVENT_TYPE, BandMemberAddedEvent.EVENT_VERSION, BandMemberAddedEvent.class, processor::process)
                .addDefaultEventHandler(message -> log.error("Could not resolve user registered message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
