package com.qsspy.finances.domain.finances;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter(AccessLevel.PACKAGE)
class CreationDate implements ValueObject {

    @Column(name = "CREATION_DATE")
    private LocalDateTime date;
    @Override
    public void validate() {
        if(date == null) {
            throw new DomainValidationException("Creation date cannot be null!");
        }
    }
}
