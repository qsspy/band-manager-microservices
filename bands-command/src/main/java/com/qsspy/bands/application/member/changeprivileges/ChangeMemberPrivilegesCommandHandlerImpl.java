package com.qsspy.bands.application.member.changeprivileges;


import com.qsspy.bands.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.application.member.changeprivileges.port.input.ChangeMemberPrivilegesCommand;
import com.qsspy.bands.application.member.changeprivileges.port.input.ChangeMemberPrivilegesCommandHandler;
import com.qsspy.bands.domain.band.Band;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class ChangeMemberPrivilegesCommandHandlerImpl implements ChangeMemberPrivilegesCommandHandler {

    private final GetBandByIdRepository getRepository;
    private final BandSaveRepository saveRepository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final ChangeMemberPrivilegesCommand command) {
        getRepository
                .findById(command.bandId())
                .ifPresentOrElse(
                        band -> changeUserPrivilegeAndSaveBand(band, command.privilegesToChange(), command.memberId()),
                        () -> { throw new IllegalStateException("Could not change privileges of the band member");}
                );
    }

    private void changeUserPrivilegeAndSaveBand(final Band band, final ChangeMemberPrivilegesCommand.Privileges privileges, final UUID memberId) {
        final var specification = CommandToDomainDtoMapper.toSpecification(privileges);
        band.changeUserPrivileges(specification, memberId);
        saveRepository.save(band);
        publisher.publishAll(band.flushEvents());
    }
}
