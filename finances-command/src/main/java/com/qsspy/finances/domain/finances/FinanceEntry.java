package com.qsspy.finances.domain.finances;

import com.qsspy.commons.architecture.ddd.AggregateRoot;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.eda.DomainEvent;
import com.qsspy.commons.messaging.DomainEventHistory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter(AccessLevel.PACKAGE)
@Entity(name = "FINANCE_ENTRIES")
public class FinanceEntry implements AggregateRoot {

    @EmbeddedId
    private AggregateId id;

    @Embedded
    private BandId bandId;

    @Embedded
    private Amount amount;

    @Embedded
    @Nullable
    private Description description;

    @Embedded
    private Initiator initiator;

    @Embedded
    private CreationDate creationDate;

    @Column(name = "IS_OUTCOME")
    boolean isOutcome;

    @Transient
    @Builder.Default
    private DomainEventHistory eventHistory = new DomainEventHistory();

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Aggregate id cannot be null!");
        }
        if(bandId == null) {
            throw new DomainValidationException("Band id cannot be null!");
        }
        if(initiator == null) {
            throw new DomainValidationException("Initiator cannot be null!");
        }
        if(amount == null) {
            throw new DomainValidationException("Amount cannot be null!");
        }
        if(creationDate == null) {
            throw new DomainValidationException("Creation date cannot be null!");
        }

        id.validate();
        bandId.validate();
        amount.validate();
        initiator.validate();
        creationDate.validate();
        if(description != null) {
            description.validate();
        }
    }

    FinanceEntry generateInitialEvents() {
        final var creationEvent = DomainEventFactory.buildBandDefaultPrivilegesChangedEvent(this);
        eventHistory.register(creationEvent);
        return this;
    }

    public List<DomainEvent> flushEvents() {
        return eventHistory.flush();
    }
}
