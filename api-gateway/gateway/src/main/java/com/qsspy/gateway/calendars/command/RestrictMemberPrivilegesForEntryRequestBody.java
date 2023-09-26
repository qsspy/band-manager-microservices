package com.qsspy.gateway.calendars.command;

record RestrictMemberPrivilegesForEntryRequestBody(
        boolean canSeeCalendarEntry,
        boolean canSeeCalendarEntryPayment,
        boolean canSeeCalendarEntryDetails
) { }
