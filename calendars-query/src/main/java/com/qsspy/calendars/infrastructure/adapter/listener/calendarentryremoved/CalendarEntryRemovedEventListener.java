package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremoved;

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
class CalendarEntryRemovedEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryRemovedEvent>> processors;

    @Bean
    SynchronousMessageRouter calendarEntryRemovedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(CalendarEntryRemovedEvent.EVENT_TYPE, CalendarEntryRemovedEvent.EVENT_VERSION, CalendarEntryRemovedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve calendar entry added message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
