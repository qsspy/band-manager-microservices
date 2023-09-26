package com.qsspy.bands.domain.band;

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
class CanSeeCalendarEntryByDefaultPrivilege implements ValueObject {

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_BY_DEFAULT")
    private boolean isAllowed;

    static CanSeeCalendarEntryByDefaultPrivilege from(final boolean isAllowed) {
        return new CanSeeCalendarEntryByDefaultPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
