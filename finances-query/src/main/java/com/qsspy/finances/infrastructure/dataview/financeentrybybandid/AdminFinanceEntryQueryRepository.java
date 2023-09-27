package com.qsspy.finances.infrastructure.dataview.financeentrybybandid;


import com.qsspy.finances.application.entries.port.output.FinanceEntryQueryRepository;
import com.qsspy.finances.application.entries.port.output.dto.FinanceEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class AdminFinanceEntryQueryRepository implements FinanceEntryQueryRepository {

    private final FinanceEntryByBandIdCassandraRepository repository;

    @Override
    public List<FinanceEntryDTO> getFinanceEntries(final UUID bandId, final UUID memberId) {
        return repository.findByKey_BandId(bandId)
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }
}
