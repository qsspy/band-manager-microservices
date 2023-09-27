package com.qsspy.bands.infrastructure.port.repository;

import com.qsspy.bands.application.defaultprivileges.port.output.BandDefaultPrivilegesRepository;
import com.qsspy.bands.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.application.userprivileges.port.output.BandMemberPrivilegesRepository;
import com.qsspy.bands.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CassandraBandQueryRepository implements BandMemberPrivilegesRepository {

    @Override
    public Optional<BandMemberPrivilegesDTO> finBandMemberPrivileges(final UUID bandId, final UUID memberId) {
        return Optional.empty();
    }
}
