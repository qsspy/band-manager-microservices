package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryemptyrestrictioncreated;

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
class CalendarEntryEmptyRestrictionCreatedEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryMemberEmptyRestrictionCreatedEvent>> processors;

    @Bean
    ParallelMessageRouter calendarEntryMemberEmptyRestrictionCreatedMessageRouter(final ObjectMapper objectMapper) {
        return ParallelMessageRouter.ofVirtualExecutor(objectMapper)
                .addEventHandler(CalendarEntryMemberEmptyRestrictionCreatedEvent.EVENT_TYPE, CalendarEntryMemberEmptyRestrictionCreatedEvent.EVENT_VERSION, CalendarEntryMemberEmptyRestrictionCreatedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler((message, headers) -> log.error("Could not resolve empty restriction for member, message : {}, headers: {}", message, headers))
                .enableLoggingOnMessageReceived();
    }
}
