package com.qsspy.bands.infrastucture.adapter.listener.userregistered;

import com.qsspy.bands.domain.user.User;

public interface UserSaveRepository {

    /**
     * Saves user in repository
     *
     * @param user user to be saved
     */
    void save(final User user);
}
