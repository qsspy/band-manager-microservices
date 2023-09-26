package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberremoved;

import java.util.UUID;

public interface RemoveUserBandMembershipRepository {

    /**
     * Removes user band membership information
     *
     * @param userId id of the user
     */
    void removeBandMemberId(final UUID userId);
}
