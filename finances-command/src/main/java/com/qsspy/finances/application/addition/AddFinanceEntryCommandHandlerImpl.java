package com.qsspy.finances.application.addition;

import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import com.qsspy.finances.application.addition.port.input.AddFinanceEntryCommand;
import com.qsspy.finances.application.addition.port.input.AddFinanceEntryCommandHandler;
import com.qsspy.finances.application.addition.port.output.FinanceEntrySaveRepository;
import com.qsspy.finances.domain.finances.FinanceEntryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class AddFinanceEntryCommandHandlerImpl implements AddFinanceEntryCommandHandler {

    private final FinanceEntrySaveRepository repository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final AddFinanceEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only add entries to its own band!");
        }

        final var specification = CommandToDtoMapper.toSpecification(command);
        final var entry = FinanceEntryFactory.createNewFinanceEntry(specification);
        repository.save(entry);
        publisher.publishAll(entry.flushEvents());
    }
}
