package com.qsspy.bands.application.member.addition;

import com.qsspy.bands.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.application.common.port.output.BandUserGetRepository;
import com.qsspy.bands.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.application.member.addition.port.input.AddBandMemberCommand;
import com.qsspy.bands.application.member.addition.port.input.AddBandMemberCommandHandler;
import com.qsspy.bands.domain.band.Band;
import com.qsspy.bands.domain.user.User;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import com.qsspy.commons.port.output.publisher.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
class AddBandMemberCommandHandlerImpl implements AddBandMemberCommandHandler {

    private final GetBandByIdRepository getRepository;
    private final BandSaveRepository saveRepository;
    private final BandUserGetRepository bandUserGetRepository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final AddBandMemberCommand command) {
        command.validate();

        bandUserGetRepository.findByEmail(command.userEmail()).ifPresentOrElse(
                user -> {

                    if(user.getMemberBand() != null || user.getOwnedBand() != null) {
                        throw new CommandValidationException("User is member of another band already!");
                    }

                    getRepository
                            .findById(command.bandId())
                            .ifPresentOrElse(
                                    band -> this.updateBandMemberAndSave(band, user),
                                    () -> { throw new IllegalStateException("Could not find band with given id"); }
                            );

                },
                () -> { throw new IllegalStateException("Could not find user with provided email"); }
        );
    }

    private void updateBandMemberAndSave(final Band band, final User userToAdd) {
        band.addMember(userToAdd);
        saveRepository.save(band);
        publisher.publishAll(band.flushEvents());
    }
}
