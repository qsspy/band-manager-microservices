package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface JpaBandMemberPrivilegesRepository extends JpaRepository<BandMemberPrivileges, BandMemberPrivilegesId> {

    List<BandMemberPrivileges> findAllById_BandId(final UUID bandId);

    @Modifying
    void deleteById_BandIdAndId_MemberId(final UUID bandId, final UUID memberId);
}
