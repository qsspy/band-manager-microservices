package com.qsspy.bands.infrastructure.port.repository;

import com.qsspy.bands.application.defaultprivileges.port.output.BandDefaultPrivilegesRepository;
import com.qsspy.bands.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.application.members.port.output.BandMembersGetRepository;
import com.qsspy.bands.application.members.port.output.dto.BandMemberDTO;
import com.qsspy.bands.application.userprivileges.port.output.BandMemberPrivilegesRepository;
import com.qsspy.bands.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
class CassandraBandRepository implements BandMembersGetRepository, BandDefaultPrivilegesRepository, BandMemberPrivilegesRepository {
    @Override
    public Optional<BandDefaultPrivilegesDTO> finBandDefaultPrivileges(final UUID bandID) {
        return Optional.empty();
    }

    @Override
    public List<BandMemberDTO> findBandMembers(final UUID bandId) {
        return null;
    }

    @Override
    public Optional<BandMemberPrivilegesDTO> finBandMemberPrivileges(final UUID bandId, final UUID memberId) {
        return Optional.empty();
    }
}
