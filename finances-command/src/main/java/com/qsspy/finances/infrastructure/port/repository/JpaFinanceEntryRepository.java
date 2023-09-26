package com.qsspy.finances.infrastructure.port.repository;

import com.qsspy.finances.domain.finances.FinanceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface JpaFinanceEntryRepository extends JpaRepository<FinanceEntry, UUID> {
}
