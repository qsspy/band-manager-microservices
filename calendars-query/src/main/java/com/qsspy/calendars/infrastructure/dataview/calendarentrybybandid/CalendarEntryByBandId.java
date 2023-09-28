package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("calendar_entry_by_band_id")
@Builder
@Getter
class CalendarEntryByBandId {

    @PrimaryKey
    private Id key;

    @Column("event_kind")
    private String eventKind;

    @Column("payment_amount")
    private BigDecimal paymentAmount;

    @PrimaryKeyClass
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Id {
        @PrimaryKeyColumn(name = "band_id", type = PrimaryKeyType.PARTITIONED)
        private UUID bandId;

        @PrimaryKeyColumn(name = "creation_time", type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.DESCENDING)
        private LocalDateTime eventDate;

        @PrimaryKeyColumn(name = "entry_id", type = PrimaryKeyType.CLUSTERED, ordinal = 2, ordering = Ordering.ASCENDING)
        private UUID eventId;
    }
}
