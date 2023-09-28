package com.qsspy.finances.infrastructure.adapter.listener.memberfinanceentryupdated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.messaging.ParallelMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
class MemberFinanceEntryUpdatedEventEventListener {

    private final List<DataPropagationEventProcessor<MemberFinanceEntryUpdatedEvent>> processors;

    @Bean
    ParallelMessageRouter financeEntryForMemberUpdatedMessageRouter(final ObjectMapper objectMapper) {
        return ParallelMessageRouter.ofVirtualExecutor(objectMapper)
                .addEventHandler(MemberFinanceEntryUpdatedEvent.EVENT_TYPE, MemberFinanceEntryUpdatedEvent.EVENT_VERSION, MemberFinanceEntryUpdatedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler((message, headers) -> log.error("Could not resolve finance entry update for user, message : {}, headers : {}", message, headers))
                .enableLoggingOnMessageReceived();
    }
}
