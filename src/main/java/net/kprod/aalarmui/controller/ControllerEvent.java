package net.kprod.aalarmui.controller;

import net.kprod.aalarmui.bean.Event;
import net.kprod.aalarmui.service.ServiceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/motion")
    public void eventMotion(@RequestParam String captionFilename) {
        LOG.debug("event motion received caption [{}]", captionFilename);
    }

    @RequestMapping("/state")
    public void eventState(@RequestParam String state) {
        serviceEvent.recordEventState(state);
    }

    @RequestMapping("/sensor")
    public void eventSensor(@RequestParam String sensor, @RequestParam String event) {
        serviceEvent.recordEventSensor(sensor, event);
    }

    @RequestMapping("/list")
    public List<Event> list() {
        return serviceEvent.listEventAll();
    }
}
