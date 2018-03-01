package net.kprod.aalarmui.controller;

import io.swagger.annotations.ApiOperation;
import net.kprod.aalarmui.bean.Event;
import net.kprod.aalarmui.service.ServiceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "Report motion event")
    @RequestMapping(value = "/motion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void eventMotion(@RequestParam String captionFilename) throws Exception {
        serviceEvent.recordEventMotion(captionFilename);
    }

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
    public List<Event> list() throws Exception {
        return serviceEvent.listEventAll();
    }
}
