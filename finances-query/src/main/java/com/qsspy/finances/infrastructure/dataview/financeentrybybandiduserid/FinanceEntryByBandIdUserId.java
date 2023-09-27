package com.qsspy.finances.infrastructure.dataview.financeentrybybandiduserid;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@Table("finance_entry_by_band_id_user_id")
@Getter
@Builder
class FinanceEntryByBandIdUserId {

    @PrimaryKey
    private FinanceEntryByBandIdUserIdKey key;

    @Column("amount")
    private BigDecimal amount;

    @Nullable
    @Column("description")
    private String description;

    @Column("initiator_email")
    private String initiatorEmail;

    @Column("is_outcome")
    private boolean isOutcome;

    @Column("is_visible")
    private boolean isVisible;
}