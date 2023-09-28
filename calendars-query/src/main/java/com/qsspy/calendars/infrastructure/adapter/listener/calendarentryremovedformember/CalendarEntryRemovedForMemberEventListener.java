package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremovedformember;

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
class CalendarEntryRemovedForMemberEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryRemovedForMemberEvent>> processors;

    @Bean
    ParallelMessageRouter calendarEntryForMemberRemovedMessageRouter(final ObjectMapper objectMapper) {
        return ParallelMessageRouter.ofVirtualExecutor(objectMapper)
                .addEventHandler(CalendarEntryRemovedForMemberEvent.EVENT_TYPE, CalendarEntryRemovedForMemberEvent.EVENT_VERSION, CalendarEntryRemovedForMemberEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler((message, header) -> log.error("Could not resolve calendar entry removed for member, message : {}, header: {}", message, header))
                .enableLoggingOnMessageReceived();
    }
}
