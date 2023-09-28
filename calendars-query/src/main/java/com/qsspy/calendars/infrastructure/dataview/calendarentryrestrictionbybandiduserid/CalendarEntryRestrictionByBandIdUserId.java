package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.lang.Nullable;

@Table("calendar_entry_restriction_by_band_id_user_id")
@Builder
@Getter
class CalendarEntryRestrictionByBandIdUserId {

    @PrimaryKey
    private CalendarEntryRestrictionByBandIdUserIdKey key;

    @Column("event_kind")
    private String eventKind;

    @Column("member_email")
    private String memberEmail;

    @Nullable
    @Column("is_visible")
    private Boolean isVisible;

    @Nullable
    @Column("is_visible_details")
    private Boolean isVisibleDetails;

    @Nullable
    @Column("is_visible_payment_amount")
    private Boolean isVisiblePaymentAmount;
}
