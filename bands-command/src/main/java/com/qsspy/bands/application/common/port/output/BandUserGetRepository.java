package com.qsspy.bands.application.common.port.output;

import com.qsspy.bands.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface BandUserGetRepository {

    /**
     * Finds user by id
     *
     * @param userId id of the user
     * @return user
     */
    Optional<User> findById(final UUID userId);

    /**
     * Finds user by email
     *
     * @param email of the user
     * @return user
     */
    Optional<User> findByEmail(final String email);
}
