package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import java.util.UUID;

record DefaultCalendarEntryUserPrivilegesDTO(
        UUID userId,
        boolean isVisible,
        boolean isVisibleDetails,
        boolean isVisiblePaymentAmount
) { }
