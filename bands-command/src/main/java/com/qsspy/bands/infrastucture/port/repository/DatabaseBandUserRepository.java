package com.qsspy.bands.infrastucture.port.repository;

import com.qsspy.bands.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.application.common.port.output.BandUserGetRepository;
import com.qsspy.bands.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseBandUserRepository implements BandUserGetRepository {

    private final JpaBandUserRepository userRepository;

    @Override
    public Optional<User> findById(final UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }
}
