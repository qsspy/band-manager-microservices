package com.qsspy.finances.infrastructure.dataview.financeentrybybandid;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@Table("finance_entry_by_band_id")
@Getter
@Builder
class FinanceEntryByBandId {

    @PrimaryKey
    private FinanceEntryByBandIdKey key;

    @Column("amount")
    private BigDecimal amount;

    @Nullable
    @Column("description")
    private String description;

    @Column("initiator_email")
    private String initiatorEmail;

    @Column("is_outcome")
    private boolean isOutcome;
}