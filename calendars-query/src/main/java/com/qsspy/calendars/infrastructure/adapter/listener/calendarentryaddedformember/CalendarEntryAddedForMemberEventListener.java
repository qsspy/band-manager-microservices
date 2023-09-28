package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryaddedformember;

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
class CalendarEntryAddedForMemberEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryAddedForMemberEvent>> processors;

    @Bean
    ParallelMessageRouter calendarEntryForMemberAddedMessageRouter(final ObjectMapper objectMapper) {
        return ParallelMessageRouter.ofVirtualExecutor(objectMapper)
                .addEventHandler(CalendarEntryAddedForMemberEvent.EVENT_TYPE, CalendarEntryAddedForMemberEvent.EVENT_VERSION, CalendarEntryAddedForMemberEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler((message, header) -> log.error("Could not resolve calendar entry added for member, message : {}, header: {}", message, header))
                .enableLoggingOnMessageReceived();
    }
}
