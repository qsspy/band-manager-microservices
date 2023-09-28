package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryadded;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryemptyrestrictioncreated.CalendarEntryMemberEmptyRestrictionCreatedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.messaging.ParallelMessageRouter;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
class CalendarEntryAddedEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryAddedEvent>> processors;

    @Bean
    SynchronousMessageRouter calendarEntryAddedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(CalendarEntryAddedEvent.EVENT_TYPE, CalendarEntryAddedEvent.EVENT_VERSION, CalendarEntryAddedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve calendar entry added message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
