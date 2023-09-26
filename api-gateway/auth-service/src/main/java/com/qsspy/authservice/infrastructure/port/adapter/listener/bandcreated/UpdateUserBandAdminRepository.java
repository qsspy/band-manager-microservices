package com.qsspy.authservice.infrastructure.port.adapter.listener.bandcreated;

import java.util.UUID;

public interface UpdateUserBandAdminRepository {

    /**
     * Updates user owned band information
     *
     * @param userId id of the user
     * @param bandId id of the band
     */
    void updateBandAdminId(final UUID userId, final UUID bandId);
}
