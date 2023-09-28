package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

@Table("calendar_entry_details_by_band_id_user_id_event_id")
@Builder
@Getter
class CalendarEntryDetailsByBandIdUserId {

    @PrimaryKey
    private Id key;

    @Column("address")
    @Nullable
    private String address;

    @Column("hours_duration")
    @Nullable
    private Long hoursDuration;

    @Column("description")
    @Nullable
    private String description;

    @Column("is_visible")
    private boolean isVisible;

    @PrimaryKeyClass
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Id {
        @PrimaryKeyColumn(name = "band_id", type = PrimaryKeyType.PARTITIONED)
        private UUID bandId;

        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED)
        private UUID userId;

        @PrimaryKeyColumn(name = "entry_id", type = PrimaryKeyType.PARTITIONED)
        private UUID eventId;
    }
}
