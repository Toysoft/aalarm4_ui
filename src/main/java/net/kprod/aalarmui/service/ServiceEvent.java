package net.kprod.aalarmui.service;

import net.kprod.aalarmui.bean.Event;
import net.kprod.aalarmui.enums.EnumEventType;
import net.kprod.aalarmui.exception.ServiceException;

import java.util.List;

/**
 * Created by kemkem on 3/1/18.
 */
public interface ServiceEvent {
    void recordEventState(String state) throws ServiceException;
    void recordEventSensor(String emmiterId, String status) throws ServiceException;
    void recordEventMotion(String path) throws ServiceException;
    List<Event> listEventAll() throws ServiceException;
    List<Event> listEventByType(EnumEventType eventType) throws ServiceException;
}
