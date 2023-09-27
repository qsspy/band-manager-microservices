package com.qsspy.authservice.application.register;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import com.qsspy.authservice.application.register.port.input.RegisterCommandHandler;
import com.qsspy.authservice.application.register.port.input.UserAlreadyExistsException;
import com.qsspy.authservice.application.register.port.output.UserRegisterRepository;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
class RegisterCommandHandlerImpl implements RegisterCommandHandler {

    private final UserRegisterRepository registerRepository;
    private final NotificationEventPublisher notificationEventPublisher;

    @Override
    public void handle(final RegisterCommand command) {
        command.validate();

        if(registerRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException();
        }

        final var user = UserEntityMapper.mapFromCommand(command);
        final var event = EventMapper.toEvent(user);

        registerRepository.save(user);
        notificationEventPublisher.publish(event);
    }
}
