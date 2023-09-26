package com.qsspy.calendars.infrastructure.adapter.controller;

import com.qsspy.calendars.application.entry.create.port.input.CreateCalendarEntryCommand;
import com.qsspy.calendars.application.entry.edit.port.input.EditCalendarEntryCommand;
import com.qsspy.calendars.application.entry.remove.port.input.RemoveCalendarEntryCommand;
import com.qsspy.calendars.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static CreateCalendarEntryCommand toCommand(
            final CalendarEntryRequestBody request,
            final UUID initiatorsBandId,
            final UUID bandId
            ) {
        return CreateCalendarEntryCommand.builder()
                .initiatorsBandId(initiatorsBandId)
                .bandId(bandId)
                .eventDate(request.eventDate())
                .eventKind(request.eventKind())
                .amount(request.amount())
                .address(request.address())
                .eventDuration(request.eventDurationHours() != null ? Duration.ofHours(request.eventDurationHours()) : null)
                .description(request.description())
                .build();
    }

    static RemoveCalendarEntryCommand toCommand(
            final UUID initiatorsBandId,
            final UUID bandId,
            final UUID entryId
    ) {
        return RemoveCalendarEntryCommand.builder()
                .initiatorsBandId(initiatorsBandId)
                .bandId(bandId)
                .entryId(entryId)
                .build();
    }

    static EditCalendarEntryCommand toCommand(
            final CalendarEntryRequestBody request,
            final UUID initiatorsBandId,
            final UUID bandId,
            final UUID entryId
    ) {
        return EditCalendarEntryCommand.builder()
                .initiatorsBandId(initiatorsBandId)
                .bandId(bandId)
                .entryId(entryId)
                .eventDate(request.eventDate())
                .eventKind(request.eventKind())
                .amount(request.amount())
                .address(request.address())
                .eventDuration(request.eventDurationHours() != null ? Duration.ofHours(request.eventDurationHours()) : null)
                .description(request.description())
                .build();
    }

    static RestrictMemberPrivilegesForEntryCommand toCommand(
            final RestrictMemberPrivilegesForEntryRequestBody request,
            final UUID initiatorsBandId,
            final UUID bandId,
            final UUID entryId,
            final UUID memberId
    ) {
        return RestrictMemberPrivilegesForEntryCommand.builder()
                .initiatorsBandId(initiatorsBandId)
                .bandId(bandId)
                .memberId(memberId)
                .entryId(entryId)
                .canSeeCalendarEntry(request.canSeeCalendarEntry())
                .canSeeCalendarEntryPayment(request.canSeeCalendarEntryPayment())
                .canSeeCalendarEntryDetails(request.canSeeCalendarEntryDetails())
                .build();
    }
}
