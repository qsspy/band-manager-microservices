package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@PrimaryKeyClass
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
class CalendarEntryRestrictionByBandIdUserIdKey implements Serializable {

    @PrimaryKeyColumn(name = "band_id", type = PrimaryKeyType.PARTITIONED)
    private UUID bandId;

    @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED, ordinal = 0, ordering = Ordering.DESCENDING)
    private UUID userId;

    @PrimaryKeyColumn(name = "creation_time", type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.DESCENDING)
    private LocalDateTime eventDate;

    @PrimaryKeyColumn(name = "entry_id", type = PrimaryKeyType.CLUSTERED, ordinal = 2, ordering = Ordering.ASCENDING)
    private UUID eventId;
}