package com.qsspy.calendars.domain.calendar;

import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PACKAGE)
class CanSeeCalendarEntryPaymentPrivilege implements ValueObject {

    @Column(name = "CAN_SEE_ENTRY_PAYMENT")
    private boolean isAllowed;

    static CanSeeCalendarEntryPaymentPrivilege from(final boolean isAllowed) {
        return new CanSeeCalendarEntryPaymentPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
