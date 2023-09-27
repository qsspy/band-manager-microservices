package com.qsspy.calendars.domain.calendar;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
