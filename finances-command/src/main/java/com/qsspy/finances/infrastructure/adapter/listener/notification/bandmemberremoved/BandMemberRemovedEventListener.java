package com.qsspy.finances.infrastructure.adapter.listener.notification.bandmemberremoved;

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
class BandMemberRemovedEventListener {

    private final List<DataPropagationEventProcessor<BandMemberRemovedEvent>> processors;

    @Bean
    SynchronousMessageRouter bandMemberRemovedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(BandMemberRemovedEvent.EVENT_TYPE, BandMemberRemovedEvent.EVENT_VERSION, BandMemberRemovedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve band member removed message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
