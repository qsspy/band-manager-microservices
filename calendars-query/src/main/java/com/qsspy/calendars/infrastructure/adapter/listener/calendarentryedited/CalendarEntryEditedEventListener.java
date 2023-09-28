package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryedited;

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
class CalendarEntryEditedEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryEditedEvent>> processors;

    @Bean
    SynchronousMessageRouter calendarEntryEditedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(CalendarEntryEditedEvent.EVENT_TYPE, CalendarEntryEditedEvent.EVENT_VERSION, CalendarEntryEditedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve calendar entry added message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
