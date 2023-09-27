package com.qsspy.finances.infrastructure.adapter.listener.notification.bandmemberprivilegeschanged;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class BandMemberPrivilegesChangedEventListener {

    private final List<DataPropagationEventProcessor<BandMemberPrivilegesChangedEvent>> processors;

    @Bean
    SynchronousMessageRouter bandMemberPrivilegesChangedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandMemberPrivilegesChangedEvent.EVENT_TYPE, BandMemberPrivilegesChangedEvent.EVENT_VERSION, BandMemberPrivilegesChangedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve band member privileges changed message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
