package com.qsspy.calendars.infrastructure.eventreplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "DEFAULT_BAND_PRIVILEGES")
@Setter
@Getter
public class DefaultBandPrivileges {

    @Id
    @Column(name = "BAND_ID")
    private UUID bandId;

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_BY_DEFAULT")
    private boolean canSeeCalendarEntryByDefault;

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_PAYMENT_BY_DEFAULT")
    private boolean canSeeCalendarEntryPaymentByDefault;

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_DETAILS_BY_DEFAULT")
    private boolean canSeeCalendarEntryDetailsByDefault;
}
