package com.qsspy.calendars.domain.calendar;

import com.qsspy.calendars.domain.calendar.dto.EditCalendarEntryData;
import com.qsspy.calendars.domain.calendar.dto.RestrictedMemberPrivilegesData;
import com.qsspy.commons.architecture.ddd.AggregateRoot;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.eda.DomainEvent;
import com.qsspy.commons.messaging.DomainEventHistory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "CALENDAR_ENTRIES")
@Getter(AccessLevel.PACKAGE)
public class CalendarEntry implements AggregateRoot {
    @EmbeddedId
    private AggregateId id;

    @Embedded
    private BandId bandId;

    @Embedded
    private EventDate eventDate;

    @Column(name = "EVENT_KIND")
    @Enumerated(value = EnumType.STRING)
    private EventKind eventKind;

    @Embedded
    private Amount amount;

    @Nullable
    @Embedded
    private Address address;

    @Nullable
    @Embedded
    private EventDuration eventDuration;

    @Nullable
    @Embedded
    private Description description;

    @Transient
    @Builder.Default
    private DomainEventHistory eventHistory = new DomainEventHistory();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ID")
    private List<RestrictedCalendarViewerPrivileges> restrictedViewerPrivileges;

    public CalendarEntry editEntry(final EditCalendarEntryData editData) {

        eventDate = new EventDate(editData.eventDate());
        eventKind = editData.eventKind();
        amount = new Amount(editData.amount());
        address = editData.address() != null ? new Address(editData.address()) : null;
        eventDuration = editData.eventDuration() != null ? new EventDuration(editData.eventDuration()) : null;
        description = editData.description() != null ? new Description(editData.description()) : null;

        validateCurrentState();
        return this;
    }

    public CalendarEntry editMemberEntryPrivileges(final RestrictedMemberPrivilegesData data) {

        restrictedViewerPrivileges.stream()
                .filter(viewer -> viewer.getMemberId().equals(data.memberId()))
                .findFirst()
                .ifPresentOrElse(
                        viewer -> {
                            viewer.changeCanSeeCalendarEntryPrivilege(data.canSeeCalendarEntry());
                            viewer.changeCanSeeCalendarEntryPaymentPrivilege(data.canSeeCalendarEntryPayment());
                            viewer.changeCanSeeCalendarEntryDetailsPrivilege(data.canSeeCalendarEntryDetails());

                            final var event = DomainEventFactory.buildCalendarEntryRestrictionForMemberChangedEvent(viewer, this);
                            eventHistory.register(event);
                        },
                        () -> {

                            final var restriction = RestrictedCalendarViewerPrivileges.builder()
                                    .id(new RestrictionId(id.getValue(), data.memberId()))
                                    .canSeeCalendarEntry(CanSeeCalendarEntryPrivilege.from(data.canSeeCalendarEntry()))
                                    .canSeeCalendarEntryPayment(CanSeeCalendarEntryPaymentPrivilege.from(data.canSeeCalendarEntryPayment()))
                                    .canSeeCalendarEntryDetails(CanSeeCalendarEntryDetailsPrivilege.from(data.canSeeCalendarEntryDetails()))
                                    .build();

                            restrictedViewerPrivileges.add(restriction);
                            final var event = DomainEventFactory.buildCalendarEntryRestrictionForMemberChangedEvent(restriction, this);
                            eventHistory.register(event);
                        }
                );

        validateCurrentState();
        return this;
    }

    public List<DomainEvent> flushEvents() {
        return eventHistory.flush();
    }

    CalendarEntry generateInitialEvents() {
        final var creationEvent = DomainEventFactory.buildCalendarEntryAddedEvent(this);
        eventHistory.register(creationEvent);
        return this;
    }

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Aggregate id cannot be null!");
        }
        if(bandId == null) {
            throw new DomainValidationException("Band id cannot be null!");
        }
        if(eventDate == null) {
            throw new DomainValidationException("Event date cannot be null!");
        }
        if(eventKind == null) {
            throw new DomainValidationException("Event kind cannot be null!");
        }
        if(amount == null) {
            throw new DomainValidationException("Amount cannot be null!");
        }
        if(restrictedViewerPrivileges == null) {
            throw new DomainValidationException("Restricted viewers cannot be null!");
        }

        id.validate();
        bandId.validate();
        eventDate.validate();
        amount.validate();
        restrictedViewerPrivileges.forEach(RestrictedCalendarViewerPrivileges::validate);

        if(address != null) {
            address.validate();
        }
        if(eventDuration != null) {
            eventDuration.validate();
        }
        if(description != null) {
            description.validate();
        }
    }
}
