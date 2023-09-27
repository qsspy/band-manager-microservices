package com.qsspy.finances.infrastructure.dataview.financeentrybybandid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface FinanceEntryByBandIdCassandraRepository extends CassandraRepository<FinanceEntryByBandId, FinanceEntryByBandIdKey> {

    List<FinanceEntryByBandId> findByKey_BandId(final UUID bandId);
}
