package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberadded;

import java.util.UUID;

public interface UpdateUserBandMemberRepository {

    /**
     * Updates user band membership information
     *
     * @param userId id of the user
     * @param bandId id of the band
     */
    void updateBandMemberId(final UUID userId, final UUID bandId);
}
