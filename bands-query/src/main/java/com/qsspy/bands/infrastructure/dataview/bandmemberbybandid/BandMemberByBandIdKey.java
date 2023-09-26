package com.qsspy.bands.infrastructure.dataview.bandmemberbybandid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@PrimaryKeyClass
@EqualsAndHashCode
@Getter
@AllArgsConstructor
class BandMemberByBandIdKey implements Serializable {

    @PrimaryKeyColumn(name = "band_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID bandId;

    @PrimaryKeyColumn(name = "email", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String email;
}
