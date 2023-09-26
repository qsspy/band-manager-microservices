package com.qsspy.finances.application.addition.port.output;

import com.qsspy.finances.domain.finances.FinanceEntry;

public interface FinanceEntrySaveRepository {

    /**
     * Saves entry to the repository
     *
     * @param entry entry to be saved
     */
    void save(final FinanceEntry entry);
}
