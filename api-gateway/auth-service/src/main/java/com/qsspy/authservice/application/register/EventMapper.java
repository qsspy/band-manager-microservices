package com.qsspy.authservice.application.register;

import com.qsspy.authservice.domain.user.User;
import com.qsspy.authservice.domain.user.UserRegisteredEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
final class EventMapper {

    static UserRegisteredEvent toEvent(final User user) {
        return UserRegisteredEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
