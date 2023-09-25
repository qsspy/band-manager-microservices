package com.qsspy.bands.infrastucture.port.repository;

import com.qsspy.bands.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(final String email);
}
