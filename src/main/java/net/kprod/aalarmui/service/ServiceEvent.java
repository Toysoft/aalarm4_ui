package net.kprod.aalarmui.service;

import net.kprod.aalarmui.bean.DataTableData;
import net.kprod.aalarmui.bean.DataTablesRequest;
import net.kprod.aalarmui.bean.Event;
import net.kprod.aalarmui.bean.Motion;
import net.kprod.aalarmui.db.entity.EntityEvent;
import net.kprod.aalarmui.enums.EnumEventType;
import net.kprod.aalarmui.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kemkem on 3/1/18.
 */
public interface ServiceEvent {
    void recordEventState(String state) throws ServiceException;
    void recordEventSensor(String emmiterId, String status) throws ServiceException;
    void recordEventMotion(String pathImage) throws ServiceException;
    DataTableData pageEvent(DataTablesRequest dataTablesRequest);
    Event getLastAlarmState() throws ServiceException;
    Event getLastSensorStatus() throws ServiceException;
    List<Motion> listMotionAll() throws ServiceException;
    List<Motion> listMotionAroundEvent(LocalDateTime dateFrom, LocalDateTime dateTo, Long idEvent) throws ServiceException;
}
