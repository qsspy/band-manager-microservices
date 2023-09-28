package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.authservice.application.authorizer.port.output.UserContextRepository;
import com.qsspy.authservice.application.login.port.output.UserLoginRepository;
import com.qsspy.authservice.application.register.port.output.UserRegisterRepository;
import com.qsspy.authservice.domain.memberprivileges.BandMemberPrivileges;
import com.qsspy.authservice.domain.user.User;
import com.qsspy.authservice.infrastructure.port.adapter.listener.bandcreated.UpdateUserBandAdminRepository;
import com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberadded.UpdateUserBandMemberRepository;
import com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberprivilegeschanged.BandMemberPrivilegesSaveRepository;
import com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberremoved.RemoveUserBandMembershipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
class DatabaseUserRepository implements UserContextRepository, UserLoginRepository, UserRegisterRepository, UpdateUserBandAdminRepository, UpdateUserBandMemberRepository, BandMemberPrivilegesSaveRepository, RemoveUserBandMembershipRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserBandPrivilegesRepository bandPrivilegesRepository;

    @Override
    public Optional<UserContext> findById(final UUID userId) {
        log.info("Finding user with id : {}", userId);
        return jpaUserRepository
                .findUserContextById(userId)
                .map(userData -> {
                    if(userData.userOwnBandId() != null) {
                        return DtoMapper.toUserContext(userData, UserBandPrivilegesDto.allAllowed());
                    }

                    if(userData.userMemberBandId() == null) {
                        return DtoMapper.toUserContext(userData, null);
                    }

                    return DtoMapper.toUserContext(
                            userData,
                            bandPrivilegesRepository.findUserBandPrivileges(userId, userData.userMemberBandId())
                    );
                });
    }

    @Override
    public Optional<UUID> getUserIdByCredentials(final String email, final String password) {
        return jpaUserRepository.findUserIdByEmailAndPassword(email, password);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public void save(final User user) {
        jpaUserRepository.save(user);
    }

    @Override
    public void updateBandAdminId(final UUID userId, final UUID bandId) {
        jpaUserRepository.updateOwnBandIdById(bandId, userId);
    }

    @Override
    public void updateBandMemberId(final UUID userId, final UUID bandId) {
        jpaUserRepository.updateMemberBandIdById(bandId, userId);
    }

    @Override
    public void save(BandMemberPrivileges privileges) {
        bandPrivilegesRepository.save(privileges);
    }

    @Override
    public void removeBandMemberId(final UUID userId) {
        jpaUserRepository.updateMemberBandIdById(null, userId);
    }
}
