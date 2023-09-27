package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
class BandMemberPrivilegesId implements Serializable {

    @Column(name = "BAND_ID")
    private UUID bandId;

    @Column(name = "MEMBER_ID")
    private UUID memberId;
}
