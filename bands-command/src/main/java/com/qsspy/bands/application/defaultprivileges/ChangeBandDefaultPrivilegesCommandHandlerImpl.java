package com.qsspy.bands.application.defaultprivileges;

import com.qsspy.bands.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommand;
import com.qsspy.bands.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommandHandler;
import com.qsspy.bands.domain.band.Band;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.MeasurementNotificationEvent;
import com.qsspy.commons.port.output.publisher.notification.MeasurementType;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
class ChangeBandDefaultPrivilegesCommandHandlerImpl implements ChangeBandDefaultPrivilegesCommandHandler {

    private final GetBandByIdRepository getRepository;
    private final BandSaveRepository saveRepository;
    private final DomainEventPublisher publisher;
    private final NotificationEventPublisher notificationEventPublisher;

    @Override
    public void handle(final ChangeBandDefaultPrivilegesCommand command) {
        command.validate();

        getRepository
                .findById(command.bandId())
                .ifPresentOrElse(
                        band -> changeBandDefaultPrivilegesAndSave(band, command),
                        () -> {
                            throw new IllegalStateException("Could not find band to change default privileges");
                        }
                );
    }

    private void changeBandDefaultPrivilegesAndSave(final Band band, final ChangeBandDefaultPrivilegesCommand command) {
        final var privilegeSpecification = CommandToDomainDtoMapper.toSpecification(command);
        band.changeDefaultPrivileges(privilegeSpecification);
        saveRepository.save(band);

        notificationEventPublisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.DEFAULT_PRIVILEGES_REPLICATED
        ));

        publisher.publishAll(band.flushEvents());
    }
}
