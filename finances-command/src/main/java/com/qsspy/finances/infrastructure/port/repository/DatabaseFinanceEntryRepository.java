package com.qsspy.finances.infrastructure.port.repository;

import com.qsspy.finances.application.addition.port.output.FinanceEntrySaveRepository;
import com.qsspy.finances.domain.finances.FinanceEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DatabaseFinanceEntryRepository implements FinanceEntrySaveRepository {

    private final JpaFinanceEntryRepository repository;

    @Override
    public void save(final FinanceEntry entry) {
        repository.save(entry);
    }
}
