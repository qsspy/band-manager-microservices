package com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberremoved;

import com.qsspy.calendars.domain.calendar.RestrictedCalendarViewerPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface JpaRestrictionRemovalRepository extends JpaRepository<RestrictedCalendarViewerPrivileges, Void> {

    void deleteAllById_MemberId(final UUID memberId);
}
