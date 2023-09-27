package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import java.util.List;
import java.util.UUID;

public interface VisibilityFinanceEntryRepository {

    List<FinanceEntryDTO> findEntriesByBandIdAndIsOutcome(final UUID bandId);
    List<FinanceEntryDTO> findEntriesByBandIdAndIsIncome(final UUID bandId);

    List<FinanceEntryDTO> findByBandId(final UUID bandId);
}
