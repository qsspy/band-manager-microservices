package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberprivilegeschanged.CalendarEntryMemberPrivilegesChangedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.MeasurementNotificationEvent;
import com.qsspy.commons.port.output.publisher.notification.MeasurementType;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
class CalendarEntryMemberPrivilegesChangedDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryMemberPrivilegesChangedEvent> {

    private final CalendarEntryDetailsByBandIdUserIdCassandraRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryMemberPrivilegesChangedEvent event) {
        repository.updateEntryDetailsViewPrivilege(
                event.isVisibleDetails(),
                event.bandId(),
                event.memberId(),
                event.entryId()
        );

        publisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.DEFAULT_PRIVILEGES_REPLICATED
        ));
    }
}
