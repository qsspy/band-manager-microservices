package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "BAND_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
class BandMemberEntity {

    @EmbeddedId
    private Id id;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    static class Id implements Serializable {
        @Column(name = "BAND_ID")
        private UUID bandId;

        @Column(name = "MEMBER_ID")
        private UUID memberId;
    }
}
