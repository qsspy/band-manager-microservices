package com.qsspy.calendars.application.entries.list.port.input;

import com.qsspy.commons.architecture.cqrs.Query;
import com.qsspy.commons.architecture.cqrs.QueryValidationException;

import java.util.UUID;

public record GetCalendarEntryListQuery(
        UUID bandId,
        UUID memberId,
        boolean isAdmin
) implements Query {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new QueryValidationException("Band Id cannot be null");
        }
        if(memberId == null) {
            throw new QueryValidationException("Member Id cannot be null");
        }
    }
}
