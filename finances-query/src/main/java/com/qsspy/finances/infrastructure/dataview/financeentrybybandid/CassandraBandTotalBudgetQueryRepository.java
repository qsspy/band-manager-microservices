package com.qsspy.finances.infrastructure.dataview.financeentrybybandid;

import com.qsspy.finances.application.entries.port.output.BandTotalBudgetQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CassandraBandTotalBudgetQueryRepository implements BandTotalBudgetQueryRepository {

    private final FinanceEntryByBandIdCassandraRepository repository;

    @Override
    public BigDecimal getBandTotalBudget(final UUID bandId) {
        return repository
                .findByKey_BandId(bandId)
                .stream()
                .map(FinanceEntryByBandId::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
