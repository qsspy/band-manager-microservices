package com.qsspy.finances.infrastructure.dataview.financeentrybybandid;

import com.qsspy.finances.application.entries.port.output.dto.FinanceEntryDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static FinanceEntryDTO toDto(final FinanceEntryByBandId entity) {
        return FinanceEntryDTO.builder()
                .entryId(entity.getKey().getEntryId())
                .amount(entity.getAmount())
                .description(entity.getDescription())
                .initiatorEmail(entity.getInitiatorEmail())
                .isOutcome(entity.isOutcome())
                .build();
    }
}
