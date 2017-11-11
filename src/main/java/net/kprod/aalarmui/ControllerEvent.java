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

    @RequestMapping("/event")
    public void event(@RequestParam String type) {
        LOG.debug("event received " + type);
    }
}
