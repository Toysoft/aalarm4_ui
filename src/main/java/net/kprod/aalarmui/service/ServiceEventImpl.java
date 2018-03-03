package net.kprod.aalarmui.service;

import net.kprod.aalarmui.bean.*;
import net.kprod.aalarmui.db.entity.EntityEvent;
import net.kprod.aalarmui.db.entity.EntityEventMotion;
import net.kprod.aalarmui.db.repository.RepositoryEvent;
import net.kprod.aalarmui.db.repository.RepositoryEventMotion;
import net.kprod.aalarmui.db.repository.RepositoryPageableEvent;
import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.enums.EnumEventType;
import net.kprod.aalarmui.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
    private RepositoryPageableEvent repositoryPageableEvent;

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
    private EntityEvent recordEvent(String emmiterId, EnumEventStatus status) {
        EntityEvent entityEvent = new EntityEvent(LocalDateTime.now(), emmiterId, status);
        LOG.debug("Record event [{}] from [{}]", status, emmiterId);
        return repositoryEvent.save(entityEvent);
    }

    @Override
    @Transactional
    public void recordEventMotion(String pathImage) {
        Path path = Paths.get(pathImage);
        String filename = path.getFileName().toString();

        EntityEvent entityEvent = recordEvent(EnumEventType.camera.name(), EnumEventStatus.motion);

        EntityEventMotion entityEventMotion = new EntityEventMotion(entityEvent, LocalDateTime.now(), filename);
        repositoryEventMotion.save(entityEventMotion);
    }

    @Override
    public DataTableData pageEvent(DataTablesRequest dataTablesRequest) {
        Optional<String> orderBy = Optional.empty();
        Optional<Sort.Direction> orderDir = Optional.empty();

        if(!dataTablesRequest.getOrders().isEmpty()) {
            orderBy = Optional.of(dataTablesRequest.getColumns().get(dataTablesRequest.getOrders().get(0).getColumn()).getData());
            orderDir = Optional.of(dataTablesRequest.getOrders().get(0).getDir().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC);
        }

        int pageNumber = dataTablesRequest.getStart() / dataTablesRequest.getLength();

        Pageable pageable = new PageRequest(pageNumber, dataTablesRequest.getLength(),
                orderDir.orElse(Sort.Direction.DESC),
                orderBy.orElse("id"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        UiDefaults uiDefaults = new UiDefaults();

        if(StringUtils.isEmpty(dataTablesRequest.getDateFrom())) {
            dataTablesRequest.setDateFrom(uiDefaults.getDateFrom());
        }
        if(StringUtils.isEmpty(dataTablesRequest.getDateTo())) {
            dataTablesRequest.setDateTo(uiDefaults.getDateTo());
        }

        LocalDateTime dateFrom = LocalDateTime.parse(dataTablesRequest.getDateFrom(), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(dataTablesRequest.getDateTo(), formatter);

        Page<EntityEvent> pageEntityEvent = repositoryPageableEvent.findByDateEventBetween(pageable, dateFrom, dateTo);

        long total = pageEntityEvent.getTotalElements();
        DataTableData data = mapEventsToDataTableData(dataTablesRequest.getDraw(), total, pageEntityEvent.getContent());

        return data;
    }

    private DataTableData mapEventsToDataTableData(int draw, long total, List<EntityEvent> listEntityEvent) {
        List<Event> listBeanEvent = listEntityEvent.stream()
                .map(e -> mapEvent(e))
                .collect(Collectors.toList());

        DataTableData d = new DataTableData();
        d.setData(listBeanEvent);
        d.setDraw(draw);
        d.setRecordsFiltered(Math.toIntExact(total));
        d.setRecordsTotal(Math.toIntExact(total));

        return d;
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

    @Override
    public List<Motion> listMotionAll() throws ServiceException {
        return repositoryEventMotion.findAll().stream()
                .map(e -> mapMotion(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<Motion> listMotionAroundEvent(LocalDateTime dateFrom, LocalDateTime dateTo, Long idEvent) throws ServiceException {


        return null;
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

    private Motion mapMotion(EntityEventMotion entityEventMotion) {
        Motion motion = new Motion()
                .setId(entityEventMotion.getId())
                .setDateEvent(entityEventMotion.getDateEvent())
                .setFilename(entityEventMotion.getFilename());
        return motion;
    }

}
