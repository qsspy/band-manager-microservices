package com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberadded;

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
class BandMemberAddedEventListener {

    private final List<DataPropagationEventProcessor<BandMemberAddedEvent>> processors;

    @Bean
    SynchronousMessageRouter bandMemberAddedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandMemberAddedEvent.EVENT_TYPE, BandMemberAddedEvent.EVENT_VERSION, BandMemberAddedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve band member removed message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
