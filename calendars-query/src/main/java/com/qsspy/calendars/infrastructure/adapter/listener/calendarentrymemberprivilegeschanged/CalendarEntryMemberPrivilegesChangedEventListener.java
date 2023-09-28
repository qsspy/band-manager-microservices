package com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberprivilegeschanged;

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
class CalendarEntryMemberPrivilegesChangedEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryMemberPrivilegesChangedEvent>> processors;

    @Bean
    ParallelMessageRouter calendarEntryMemberPrivilegesChangedMessageRouter(final ObjectMapper objectMapper) {
        return ParallelMessageRouter.ofVirtualExecutor(objectMapper)
                .addEventHandler(CalendarEntryMemberPrivilegesChangedEvent.EVENT_TYPE, CalendarEntryMemberPrivilegesChangedEvent.EVENT_VERSION, CalendarEntryMemberPrivilegesChangedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler((message, header) -> log.error("Could not resolve calendar entry privileges changed for member, message : {}, header: {}", message, header))
                .enableLoggingOnMessageReceived();
    }
}
