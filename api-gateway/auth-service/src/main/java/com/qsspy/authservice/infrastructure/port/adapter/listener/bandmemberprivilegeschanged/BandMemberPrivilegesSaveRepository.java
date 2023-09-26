package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberprivilegeschanged;

import com.qsspy.authservice.domain.memberprivileges.BandMemberPrivileges;

public interface BandMemberPrivilegesSaveRepository {

    /**
     * Saves band member privileges in repository
     *
     * @param privileges privileges to save
     */
    void save(final BandMemberPrivileges privileges);
}
