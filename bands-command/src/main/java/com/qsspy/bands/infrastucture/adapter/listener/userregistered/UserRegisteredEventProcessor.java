package com.qsspy.bands.infrastucture.adapter.listener.userregistered;

import com.qsspy.bands.domain.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class UserRegisteredEventProcessor {

    private final UserSaveRepository saveRepository;

    public void process(final UserRegisteredEvent event) {
        final var user = User.builder()
                .id(event.userId())
                .email(event.email())
                .build();

        saveRepository.save(user);
    }
}
