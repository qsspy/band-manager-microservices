package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.infrastructure.eventreplication.BandMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface JpaBandMemberEntityRepository extends JpaRepository<BandMemberEntity, BandMemberEntity.Id> {

    List<BandMemberEntity> findAllById_BandId(final UUID bandId);
}
