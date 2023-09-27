package com.qsspy.finances.infrastructure.port.repository;

import com.qsspy.finances.domain.finances.FinanceEntry;
import com.qsspy.finances.infrastructure.dataview.bandmemberprivileges.FinanceEntryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
interface JpaFinanceEntryRepository extends JpaRepository<FinanceEntry, UUID> {

    @Query("""
           SELECT new com.qsspy.finances.infrastructure.dataview.bandmemberprivileges.FinanceEntryDTO(
                f.id.value,
                f.amount.value,
                f.description.text,
                f.initiator.email,
                f.creationDate.date,
                f.isOutcome
           )
           FROM FINANCE_ENTRIES f
           WHERE f.isOutcome = TRUE
                AND f.bandId.value = :bandId
           """)
    List<FinanceEntryDTO> findEntriesIdsByBandIdAndIsOutcome(final UUID bandId);

    @Query("""
           SELECT new com.qsspy.finances.infrastructure.dataview.bandmemberprivileges.FinanceEntryDTO(
                f.id.value,
                f.amount.value,
                f.description.text,
                f.initiator.email,
                f.creationDate.date,
                f.isOutcome
           )
           FROM FINANCE_ENTRIES f
           WHERE f.isOutcome = FALSE
                AND f.bandId.value = :bandId
           """)
    List<FinanceEntryDTO> findEntriesIdsByBandIdAndIsIncome(final UUID bandId);

    @Query("""
           SELECT new com.qsspy.finances.infrastructure.dataview.bandmemberprivileges.FinanceEntryDTO(
                f.id.value,
                f.amount.value,
                f.description.text,
                f.initiator.email,
                f.creationDate.date,
                f.isOutcome
           )
           FROM FINANCE_ENTRIES f
           WHERE f.bandId.value = :bandId
           """)
    List<FinanceEntryDTO> findByBandId(final UUID bandId);
}
