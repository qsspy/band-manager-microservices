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
class CanSeeCalendarEntryPrivilege implements ValueObject {

    @Column(name = "CAN_SEE_ENTRY")
    private boolean isAllowed;

    static CanSeeCalendarEntryPrivilege from(final boolean isAllowed) {
        return new CanSeeCalendarEntryPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
