package com.qsspy.commons.architecture.eda;

import java.util.List;

public interface DomainEventProcessor<E extends DomainEvent>{

    void process(final E event);

    static <E extends DomainEvent> void processByAll(final E event, final List<DomainEventProcessor<E>> processors) {
        processors.forEach(processor -> processor.process(event));
    }
}
