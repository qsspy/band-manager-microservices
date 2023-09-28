package com.qsspy.bands.application.creation;

import com.qsspy.bands.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.application.common.port.output.BandUserGetRepository;
import com.qsspy.bands.application.creation.port.input.CreateBandCommand;
import com.qsspy.bands.application.creation.port.input.CreateBandCommandHandler;
import com.qsspy.bands.domain.band.BandFactory;
import com.qsspy.bands.domain.band.dto.BandCreationData;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class CreateBandCommandHandlerImpl implements CreateBandCommandHandler {

    private final BandSaveRepository saveRepository;
    private final BandUserGetRepository userGetRepository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final CreateBandCommand command) {
        command.validate();

        final var bandCreationData = new BandCreationData(command.creatorId(), command.bandName());

        userGetRepository.findById(command.creatorId())
                .ifPresentOrElse(
                        user -> {
                            final var band = BandFactory.createrNewBand(bandCreationData, user);
                            saveRepository.save(band);
                            publisher.publishAll(band.flushEvents());
                        },
                        () -> { throw new IllegalStateException("Could not find band creator user.");}
                );
    }
}
