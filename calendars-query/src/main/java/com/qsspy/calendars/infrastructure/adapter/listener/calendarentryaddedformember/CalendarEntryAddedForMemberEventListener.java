package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryaddedformember;

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
class CalendarEntryAddedForMemberEventListener {

    private final List<DataPropagationEventProcessor<CalendarEntryAddedForMemberEvent>> processors;

    @Bean
    SynchronousMessageRouter calendarEntryForMemberAddedMessageRouter(final ObjectMapper objectMapper) {
        return SynchronousMessageRouter.of(objectMapper)
                .addEventHandler(CalendarEntryAddedForMemberEvent.EVENT_TYPE, CalendarEntryAddedForMemberEvent.EVENT_VERSION, CalendarEntryAddedForMemberEvent.class, event -> DataPropagationEventProcessor.processByAll(event, processors))
                .addDefaultEventHandler(message -> log.error("Could not resolve calendar entry added for membermessage : {}", message))
                .enableLoggingOnMessageReceived();
    }
}
