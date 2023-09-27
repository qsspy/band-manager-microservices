package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
