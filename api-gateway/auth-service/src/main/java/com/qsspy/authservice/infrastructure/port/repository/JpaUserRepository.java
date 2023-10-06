package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.login.port.output.UserLoginDTO;
import com.qsspy.authservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaUserRepository extends JpaRepository<User, UUID> {

    @Query("""
           SELECT new com.qsspy.authservice.infrastructure.port.repository.UserDataDto(
                u.id,
                u.email,
                u.firstName,
                u.lastName,
                u.memberBandId,
                u.ownedBandId
           )
           FROM USERS u
           WHERE u.id = :id
           """)
    Optional<UserDataDto> findUserContextById(final UUID id);

    @Query("""
           SELECT new com.qsspy.authservice.application.login.port.output.UserLoginDTO(
                u.id,
                u.ownedBandId,
                u.memberBandId    
           )
           FROM USERS u
           WHERE
                u.email = :email
                AND u.password = :password
           """)
    Optional<UserLoginDTO> findUserIdByEmailAndPassword(final String email, final String password);

    boolean existsByEmail(final String email);

    @Modifying
    @Query("""
           UPDATE USERS u
           SET u.ownedBandId = :bandId
           WHERE u.id = :userId
           """)
    void updateOwnBandIdById(final UUID bandId, final UUID userId);

    @Modifying
    @Query("""
           UPDATE USERS u
           SET u.memberBandId = :bandId
           WHERE u.id = :userId
           """)
    void updateMemberBandIdById(final UUID bandId, final UUID userId);
}
