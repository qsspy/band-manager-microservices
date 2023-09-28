package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryeditedformember;

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
class CalendarEntryEditedForMemberEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryEditedForMemberEvent>> processors;

    @Bean
    ParallelMessageRouter calendarEntryForMemberEditedMessageRouter(final ObjectMapper objectMapper) {
        return ParallelMessageRouter.ofVirtualExecutor(objectMapper)
                .addEventHandler(CalendarEntryEditedForMemberEvent.EVENT_TYPE, CalendarEntryEditedForMemberEvent.EVENT_VERSION, CalendarEntryEditedForMemberEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler((message, header) -> log.error("Could not resolve calendar entry edited for member, message : {}, header: {}", message, header))
                .enableLoggingOnMessageReceived();
    }
}
