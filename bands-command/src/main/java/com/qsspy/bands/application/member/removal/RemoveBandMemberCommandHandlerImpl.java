package com.qsspy.bands.application.member.removal;

import com.qsspy.bands.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.application.member.removal.port.input.RemoveBandMemberCommand;
import com.qsspy.bands.application.member.removal.port.input.RemoveBandMemberCommandHandler;
import com.qsspy.bands.domain.band.Band;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class RemoveBandMemberCommandHandlerImpl implements RemoveBandMemberCommandHandler {

    private final GetBandByIdRepository getRepository;
    private final BandSaveRepository saveRepository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final RemoveBandMemberCommand command) {
        command.validate();

        getRepository
                .findById(command.bandId())
                .ifPresentOrElse(
                        band -> this.removeBandMemberAndSave(band, command.memberId()),
                        () -> { throw new IllegalStateException("Could not find band with given id"); }
                );
    }

    private void removeBandMemberAndSave(final Band band, final UUID bandMemberId) {
        band.removeMember(bandMemberId);
        saveRepository.save(band);
        publisher.publishAll(band.flushEvents());
    }
}
