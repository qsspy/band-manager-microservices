package com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberrestrictionremoved;

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
class CalendarEntryMemberRestrictionRemovedEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryMemberRestrictionRemovedEvent>> processors;

    @Bean
    ParallelMessageRouter calendarEntryMemberRestrictionRemovedMessageRouter(final ObjectMapper objectMapper) {
        return ParallelMessageRouter.ofVirtualExecutor(objectMapper)
                .addEventHandler(CalendarEntryMemberRestrictionRemovedEvent.EVENT_TYPE, CalendarEntryMemberRestrictionRemovedEvent.EVENT_VERSION, CalendarEntryMemberRestrictionRemovedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler((message, headers) -> log.error("Could not resolve member restriction removed, message : {}, headers: {}", message, headers))
                .enableLoggingOnMessageReceived();
    }
}
