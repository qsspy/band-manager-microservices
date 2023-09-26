package com.qsspy.commons.messaging;

import com.qsspy.commons.architecture.eda.DomainEvent;

import java.util.LinkedList;
import java.util.List;

public class DomainEventHistory {

    private List<DomainEvent> events = new LinkedList<>();

    public void register(final DomainEvent domainEvent) {
        events.add(domainEvent);
    }

    public List<DomainEvent> flush() {
        final var eventList = new LinkedList<>(events);
        events.clear();
        return eventList;
    }
}
