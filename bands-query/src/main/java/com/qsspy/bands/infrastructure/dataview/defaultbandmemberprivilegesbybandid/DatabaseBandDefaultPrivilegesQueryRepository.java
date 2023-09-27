package com.qsspy.bands.infrastructure.dataview.defaultbandmemberprivilegesbybandid;

import com.qsspy.bands.application.defaultprivileges.port.output.BandDefaultPrivilegesRepository;
import com.qsspy.bands.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseBandDefaultPrivilegesQueryRepository implements BandDefaultPrivilegesRepository {

    private final DefaultBandMemberPrivilegesByBandIdCassandraRepository repository;

    @Override
    public Optional<BandDefaultPrivilegesDTO> finBandDefaultPrivileges(final UUID bandID) {
        return repository.findById(bandID)
                .map(DtoMapper::toDto);
    }
}
