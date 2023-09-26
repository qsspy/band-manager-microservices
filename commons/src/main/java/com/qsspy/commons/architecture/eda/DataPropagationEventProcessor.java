package com.qsspy.commons.architecture.eda;

import java.util.List;

public interface DataPropagationEventProcessor<E extends DataPropagationEvent>{

    void process(final E event);

    static <E extends DataPropagationEvent> void processByAll(final E event, final List<DataPropagationEventProcessor<E>> processors) {
        processors.forEach(processor -> processor.process(event));
    }
}
