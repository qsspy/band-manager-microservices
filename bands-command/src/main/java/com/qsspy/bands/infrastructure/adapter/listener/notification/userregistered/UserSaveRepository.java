package com.qsspy.bands.infrastructure.adapter.listener.notification.userregistered;

import com.qsspy.bands.domain.user.User;

public interface UserSaveRepository {

    /**
     * Saves user in repository
     *
     * @param user user to be saved
     */
    void save(final User user);
}
