package com.qsspy.bands.infrastructure.dataview.bandmemberbybandid;

import com.qsspy.bands.application.members.port.output.BandMembersGetRepository;
import com.qsspy.bands.application.members.port.output.dto.BandMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseBandMembersQueryRepository implements BandMembersGetRepository {

    private final BandMemberByBandIdCassandraRepository repository;

    @Override
    public List<BandMemberDTO> findBandMembers(final UUID bandId) {
        return repository.findByKey_BandId(bandId).stream()
                .map(DtoMapper::toDto)
                .toList();
    }
}
