package com.qsspy.bands.infrastructure.dataview.defaultbandmemberprivilegesbybandid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface DefaultBandMemberPrivilegesByBandIdCassandraRepository extends CassandraRepository<DefaultBandMemberPrivilegesByBandId, UUID> {
}
