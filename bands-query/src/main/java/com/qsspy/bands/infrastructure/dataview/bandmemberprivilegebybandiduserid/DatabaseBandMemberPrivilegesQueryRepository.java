package com.qsspy.bands.infrastructure.dataview.bandmemberprivilegebybandiduserid;

import com.qsspy.bands.application.userprivileges.port.output.BandMemberPrivilegesRepository;
import com.qsspy.bands.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseBandMemberPrivilegesQueryRepository implements BandMemberPrivilegesRepository {

    private final BandMemberPrivilegeByBandIdUserIdCassandraRepository repository;

    @Override
    public Optional<BandMemberPrivilegesDTO> finBandMemberPrivileges(final UUID bandId, final UUID memberId) {
        return repository.findById(new BandMemberPrivilegeByBandIdUserIdKey(bandId, memberId))
                .map(DtoMapper::toDto);
    }
}
