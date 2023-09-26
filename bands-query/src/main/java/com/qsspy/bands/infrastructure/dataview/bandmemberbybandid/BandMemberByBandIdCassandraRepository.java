package com.qsspy.bands.infrastructure.dataview.bandmemberbybandid;

import com.qsspy.bands.infrastructure.dataview.bandmemberbybandid.BandMemberByBandId;
import com.qsspy.bands.infrastructure.dataview.bandmemberbybandid.BandMemberByBandIdKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface BandMemberByBandIdCassandraRepository extends CassandraRepository<BandMemberByBandId, BandMemberByBandIdKey> {

    List<BandMemberByBandId> findByKey_BandId(final UUID bandId);
}
