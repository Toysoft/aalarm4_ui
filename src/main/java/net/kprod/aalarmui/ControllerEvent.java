package net.kprod.aalarmui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kemkem on 11/11/17.
 */
@RestController
public class ControllerEvent {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/eventMotion")
    public void eventMotion(@RequestParam String captionFilename) {
        LOG.debug("event motion received caption [{}]", captionFilename);
    }

    @RequestMapping("/eventState")
    public void eventState(@RequestParam String state) {
        LOG.debug("event received state [{}]", state);
    }

    @RequestMapping("/eventSensor")
    public void eventSensor(@RequestParam String sensor, @RequestParam String event) {
        LOG.debug("event sensor received sensor [{}] event [{}]", sensor, event);
    }
}
