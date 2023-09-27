package com.qsspy.finances.infrastructure.adapter.listener.financeentryupdated;

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
class FinanceEntryUpdatedEventListener {

    private final List<DataPropagationEventProcessor<FinanceEntryUpdatedEvent>> processors;

    @Bean
    SynchronousMessageRouter financeEntryUpdatedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(FinanceEntryUpdatedEvent.EVENT_TYPE, FinanceEntryUpdatedEvent.EVENT_VERSION, FinanceEntryUpdatedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve finance entry updated message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
