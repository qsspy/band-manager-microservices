package com.qsspy.calendars.infrastructure.port.repository;

import com.qsspy.calendars.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.application.entry.common.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.application.entry.remove.port.output.CalendarEntryDeleteRepository;
import com.qsspy.calendars.domain.calendar.CalendarEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseCalendarEntryRepository implements CalendarEntrySaveRepository, CalendarEntryGetRepository, CalendarEntryDeleteRepository {

    private final JpaCalendarEntryRepository jpaRepository;

    @Override
    public void save(final CalendarEntry entry) {
        jpaRepository.save(entry);
    }


    @Override
    public Optional<CalendarEntry> findByBandIdAndId(final UUID bandId, final UUID entryId) {
        return jpaRepository.findByBandIdValueAndIdValue(bandId, entryId);
    }

    @Override
    public void remove(final UUID bandId, final UUID entryId) {
        jpaRepository.deleteByBandIdValueAndIdValue(bandId, entryId);
    }
}
