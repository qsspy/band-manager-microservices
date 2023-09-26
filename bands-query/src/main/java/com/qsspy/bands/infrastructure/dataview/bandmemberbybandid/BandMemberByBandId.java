package com.qsspy.bands.infrastructure.dataview.bandmemberbybandid;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("band_member_by_band_id")
@Getter
@Builder
class BandMemberByBandId {

    @PrimaryKey
    private BandMemberByBandIdKey key;

    @Column("user_id")
    private UUID userId;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;
}
