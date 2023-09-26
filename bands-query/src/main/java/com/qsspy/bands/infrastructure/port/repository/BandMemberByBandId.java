package com.qsspy.bands.infrastructure.port.repository;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("band_member_by_band_id")
class BandMemberByBandId {

    @PrimaryKeyColumn(name = "band_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID bandId;

    @PrimaryKeyColumn(name = "email", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String email;

    @Column("user_id")
    private UUID userId;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;
}
