package com.qsspy.finances.infrastructure.port.repository;

import com.qsspy.finances.application.addition.port.output.FinanceEntrySaveRepository;
import com.qsspy.finances.domain.finances.FinanceEntry;
import com.qsspy.finances.infrastructure.dataview.bandmemberprivileges.FinanceEntryDTO;
import com.qsspy.finances.infrastructure.dataview.bandmemberprivileges.VisibilityFinanceEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseFinanceEntryRepository implements FinanceEntrySaveRepository, VisibilityFinanceEntryRepository {

    private final JpaFinanceEntryRepository repository;

    @Override
    public void save(final FinanceEntry entry) {
        repository.save(entry);
    }

    @Override
    public List<FinanceEntryDTO> findEntriesByBandIdAndIsOutcome(final UUID bandId) {
        return repository.findEntriesIdsByBandIdAndIsOutcome(bandId);
    }

    @Override
    public List<FinanceEntryDTO> findEntriesByBandIdAndIsIncome(UUID bandId) {
        return repository.findEntriesIdsByBandIdAndIsIncome(bandId);
    }

    @Override
    public List<FinanceEntryDTO> findByBandId(final UUID bandId) {
        return repository.findByBandId(bandId);
    }
}
