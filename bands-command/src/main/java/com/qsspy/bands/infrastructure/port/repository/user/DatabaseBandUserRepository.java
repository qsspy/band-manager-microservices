package com.qsspy.bands.infrastructure.port.repository.user;

import com.qsspy.bands.application.common.port.output.BandUserGetRepository;
import com.qsspy.bands.domain.user.User;
import com.qsspy.bands.infrastructure.adapter.listener.notification.userregistered.UserSaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseBandUserRepository implements BandUserGetRepository, UserSaveRepository {

    private final JpaBandUserRepository userRepository;

    @Override
    public Optional<User> findById(final UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(final User user) {
        userRepository.save(user);
    }
}
