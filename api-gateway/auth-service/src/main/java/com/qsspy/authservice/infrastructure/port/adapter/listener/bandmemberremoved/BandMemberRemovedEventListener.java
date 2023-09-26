package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberremoved;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
class BandMemberRemovedEventListener {

    private final BandMemberRemovedEventProcessor processor;

    @Bean
    SynchronousMessageRouter bandMemberRemovedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandMemberRemovedEvent.EVENT_TYPE, BandMemberRemovedEvent.EVENT_VERSION, BandMemberRemovedEvent.class, processor::process)
                .addDefaultEventHandler(message -> log.error("Could not resolve user registered message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
