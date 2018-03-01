package net.kprod.aalarmui.service;

import net.kprod.aalarmui.bean.Event;
import net.kprod.aalarmui.db.entity.EntityEvent;
import net.kprod.aalarmui.db.entity.EntityEventMotion;
import net.kprod.aalarmui.db.repository.RepositoryEvent;
import net.kprod.aalarmui.db.repository.RepositoryEventMotion;
import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.enums.EnumEventType;
import net.kprod.aalarmui.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kemkem on 3/1/18.
 */
@Service
public class ServiceEventImpl implements ServiceEvent {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RepositoryEvent repositoryEvent;

    @Autowired
    private RepositoryEventMotion repositoryEventMotion;

    @Override
    public void recordEventState(String state) {
        this.recordEvent(EnumEventType.alarm.name(), this.getEventStatus(EnumEventType.alarm, state));
    }

    @Override
    public void recordEventSensor(String emmiterId, String status) {
        this.recordEvent(emmiterId, this.getEventStatus(EnumEventType.doorSensor, status));
    }

    private EnumEventStatus getEventStatus(EnumEventType enumEventType, String status) {
        EnumEventStatus enumEventStatus = null;
        try {
            enumEventStatus = EnumEventStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            LOG.error("Unknown event status [{}]", status);
            return null;
        }

        if(!enumEventStatus.getSensorType().equals(enumEventType)) {
            LOG.error("State [{}] is not a valid alarm", status);
            return null;
        }

        return enumEventStatus;
    }

    @Transactional
    private void recordEvent(String emmiterId, EnumEventStatus status) {
        EntityEvent entityEvent = new EntityEvent(LocalDateTime.now(), emmiterId, status);
        repositoryEvent.save(entityEvent);
        LOG.debug("Recorded event [{}] from [{}]", status, emmiterId);
    }

    @Override
    @Transactional
    public void recordEventMotion(String path) {
        EntityEventMotion entityEventMotion = new EntityEventMotion(LocalDateTime.now(), path);
        repositoryEventMotion.save(entityEventMotion);
        recordEvent(EnumEventType.camera.name(), EnumEventStatus.motion);
    }

    @Override
    public List<Event> listEventAll() {
        return repositoryEvent.findAll().stream()
                .map(e -> mapEvent(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> listEventByType(EnumEventType eventType) {
        return repositoryEvent.findAll().stream()
                .filter(e -> e.getEnumSensorStatus().getSensorType().equals(eventType))
                .map(e -> mapEvent(e))
                .collect(Collectors.toList());
    }

    @Override
    public Event getLastAlarmState() throws ServiceException {
        EntityEvent entityEvent = repositoryEvent.findFirstByEnumEventTypeIsOrderByIdDesc(EnumEventType.alarm);
        return mapEvent(entityEvent);
    }

    @Override
    public Event getLastSensorStatus() throws ServiceException {
        EntityEvent entityEvent = repositoryEvent.findFirstByEnumEventTypeIsOrderByIdDesc(EnumEventType.doorSensor);
        return mapEvent(entityEvent);
    }

    private Event mapEvent(EntityEvent entityEvent) {
        Event event = new Event()
                .setDateEvent(entityEvent.getDateEvent())
                .setEventType(entityEvent.getEnumEventType())
                .setEventStatus(entityEvent.getEnumSensorStatus())
                .setEmmiterId(entityEvent.getEmmiterId())
                .setId(entityEvent.getId());
        return event;
    }


}
