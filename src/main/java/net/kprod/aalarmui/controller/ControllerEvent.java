package net.kprod.aalarmui.controller;

import io.swagger.annotations.ApiOperation;
import net.kprod.aalarmui.bean.DataTableData;
import net.kprod.aalarmui.bean.DataTablesRequest;
import net.kprod.aalarmui.bean.Event;
import net.kprod.aalarmui.db.entity.EntityEvent;
import net.kprod.aalarmui.service.ServiceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kemkem on 11/11/17.
 */
@RestController
@RequestMapping("/event")
public class ControllerEvent {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServiceEvent serviceEvent;

    @ApiOperation(value = "Report state change")
    @RequestMapping(value = "/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void eventState(@RequestParam String state) throws Exception {
        serviceEvent.recordEventState(state);
    }

    @ApiOperation(value = "Report sensor change")
    @RequestMapping(value = "/sensor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void eventSensor(@RequestParam String sensor, @RequestParam String event) throws Exception {
        serviceEvent.recordEventSensor(sensor, event);
    }

    @ApiOperation(value = "List events")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> list() throws Exception {
        return ResponseEntity.ok(serviceEvent.listEventAll());
    }

    @ApiOperation(value = "List events")
    @RequestMapping(value = "/page", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataTableData> page(@RequestBody DataTablesRequest dataTablesRequest) throws Exception {
        return ResponseEntity.ok(serviceEvent.pageEvent(dataTablesRequest, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
    }

    @ApiOperation(value = "Last alarm state")
    @RequestMapping(value = "/alarm/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> lastAlarmState() throws Exception {
        return ResponseEntity.ok(serviceEvent.getLastAlarmState());
    }

    @ApiOperation(value = "Last sensor status")
    @RequestMapping(value = "/sensor/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> lastSensorStatus() throws Exception {
        return ResponseEntity.ok(serviceEvent.getLastSensorStatus());
    }
}
