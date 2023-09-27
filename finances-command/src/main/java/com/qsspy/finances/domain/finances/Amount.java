package com.qsspy.finances.domain.finances;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter(AccessLevel.PACKAGE)
class Amount implements ValueObject {

    @Column(name = "AMOUNT")
    private BigDecimal value;

    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Amount value cannot be null");
        }
    }
}
