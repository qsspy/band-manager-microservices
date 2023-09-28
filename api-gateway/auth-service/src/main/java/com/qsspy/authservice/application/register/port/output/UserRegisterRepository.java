package com.qsspy.authservice.application.register.port.output;

import com.qsspy.authservice.domain.user.User;

public interface UserRegisterRepository {

    /**
     * Checks if user with given email exists in the repository
     *
     * @param email user email
     * @return flag indicating if user with given email already exists
     */
    boolean existsByEmail(final String email);

    /**
     * Saves the new user in the repository
     *
     * @param user user to be saved
     */
    void save(final User user);
}
