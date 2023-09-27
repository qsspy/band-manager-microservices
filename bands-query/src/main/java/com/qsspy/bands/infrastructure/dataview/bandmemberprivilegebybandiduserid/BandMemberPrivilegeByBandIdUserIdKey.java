package com.qsspy.bands.infrastructure.dataview.bandmemberprivilegebybandiduserid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@PrimaryKeyClass
@EqualsAndHashCode
@Getter
@AllArgsConstructor
class BandMemberPrivilegeByBandIdUserIdKey implements Serializable {

    @PrimaryKeyColumn(name = "band_id", type = PrimaryKeyType.PARTITIONED)
    private UUID bandId;

    @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED)
    private UUID userId;
}
