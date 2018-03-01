package net.kprod.aalarmui.service;

import net.kprod.aalarmui.bean.Event;
import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.enums.EnumEventType;

import java.util.List;

/**
 * Created by kemkem on 3/1/18.
 */
public interface ServiceEvent {
    void recordEventState(String state);
    void recordEventSensor(String emmiterId, String status);
    void recordEvent(String emmiterId, EnumEventStatus status);
    List<Event> listEventAll();
    List<Event> listEventByType(EnumEventType eventType);
}
