package com.qsspy.calendars.infrastructure.adapter.listener.notification.banddefaultprivilegeschanged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberadded.BandMemberAddedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
class BandDefaultPrivilegesChangedEventListener {

    private final List<DataPropagationEventProcessor<BandDefaultPrivilegesChangedEvent>> processors;

    @Bean
    SynchronousMessageRouter bandMemberDefaultPrivilegesChangedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandDefaultPrivilegesChangedEvent.EVENT_TYPE, BandDefaultPrivilegesChangedEvent.EVENT_VERSION, BandDefaultPrivilegesChangedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve band default privileges changed message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
