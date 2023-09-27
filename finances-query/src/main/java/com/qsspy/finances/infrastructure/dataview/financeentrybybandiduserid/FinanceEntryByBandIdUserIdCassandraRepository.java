package com.qsspy.finances.infrastructure.dataview.financeentrybybandiduserid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface FinanceEntryByBandIdUserIdCassandraRepository extends CassandraRepository<FinanceEntryByBandIdUserId, FinanceEntryByBandIdUserIdKey> {

    List<FinanceEntryByBandIdUserId> findByKey_BandIdAndKey_UserIdAndIsVisible(final UUID bandId, final UUID memberId, final boolean isVisible);

    void deleteAllByKey_BandIdAndKey_UserId(final UUID bandId, final UUID memberId);
}
