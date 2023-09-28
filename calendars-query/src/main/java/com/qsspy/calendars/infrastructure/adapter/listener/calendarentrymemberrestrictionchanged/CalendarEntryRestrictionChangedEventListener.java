package com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberrestrictionchanged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.messaging.SynchronousMessageRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
class CalendarEntryRestrictionChangedEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryRestrictionChangedEvent>> processors;

    @Bean
    SynchronousMessageRouter calendarEntryMemberRestrictionChangedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(CalendarEntryRestrictionChangedEvent.EVENT_TYPE, CalendarEntryRestrictionChangedEvent.EVENT_VERSION, CalendarEntryRestrictionChangedEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve restriction for member changed, message : {}", message))
                .enableLoggingOnMessageReceived();
    }
}