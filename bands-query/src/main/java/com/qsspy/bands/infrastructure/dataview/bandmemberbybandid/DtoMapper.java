package com.qsspy.bands.infrastructure.dataview.bandmemberbybandid;

import com.qsspy.bands.application.members.port.output.dto.BandMemberDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static BandMemberDTO toDto(final BandMemberByBandId entity) {
        return BandMemberDTO.builder()
                .id(entity.getUserId())
                .email(entity.getKey().getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
