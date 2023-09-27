package com.qsspy.finances.infrastructure.dataview.financeentrybybandiduserid;

import com.qsspy.finances.application.entries.port.output.FinanceEntryQueryRepository;
import com.qsspy.finances.application.entries.port.output.dto.FinanceEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class MemberFinanceEntryQueryRepository implements FinanceEntryQueryRepository {

    private final FinanceEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public List<FinanceEntryDTO> getFinanceEntries(final UUID bandId, final UUID memberId) {
        return repository.findByKey_BandIdAndKey_UserIdAndIsVisible(bandId, memberId, true)
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }
}
