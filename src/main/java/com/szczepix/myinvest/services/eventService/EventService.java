package com.szczepix.myinvest.services.eventService;

import com.szczepix.myinvest.enums.BaseEventType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@Service
public class EventService {

    private HashMap<String, ArrayList<Consumer<BaseEvent>>> consumersMap = new HashMap<>();

    public void addListener(final BaseEventType eventType, final Consumer<BaseEvent> eventHandler) {
        getConsumer(eventType.getName()).add(eventHandler);
    }

    public void removeListener(final BaseEventType eventType, final Consumer<BaseEvent> eventHandler) {
        getConsumer(eventType.getName()).remove(eventHandler);
    }

    public void dispatch(final BaseEvent event) {
        List<Consumer<BaseEvent>> listByType = getConsumer(event.getEventType().getName());
        listByType.forEach(consumer -> consumer.accept(event));
    }

    private List<Consumer<BaseEvent>> getConsumer(final String key) {
        consumersMap.putIfAbsent(key, new ArrayList<>());
        return consumersMap.get(key);
    }
}
