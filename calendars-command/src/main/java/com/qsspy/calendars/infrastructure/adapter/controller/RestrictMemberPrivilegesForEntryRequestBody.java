package com.qsspy.calendars.infrastructure.adapter.controller;

record RestrictMemberPrivilegesForEntryRequestBody(
        boolean canSeeCalendarEntry,
        boolean canSeeCalendarEntryPayment,
        boolean canSeeCalendarEntryDetails
) { }
