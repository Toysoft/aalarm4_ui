package net.kprod.aalarmui.controller;

import io.swagger.annotations.ApiOperation;
import net.kprod.aalarmui.bean.Motion;
import net.kprod.aalarmui.service.ServiceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by kemkem on 11/11/17.
 */
@RestController
@RequestMapping("/motion")
public class ControllerMotion {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServiceEvent serviceEvent;

    @ApiOperation(value = "Report motion event")
    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void eventMotion(@RequestParam String captionFilename) throws Exception {
        serviceEvent.recordEventMotion(captionFilename);
    }


    @ApiOperation(value = "List events")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Motion>> list() throws Exception {
        return ResponseEntity.ok(serviceEvent.listMotionAll());
    }

    @ApiOperation(value = "List motion events around event")
    @RequestMapping(value = "/listAround", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Motion>> list2(@RequestParam String strDateFrom, @RequestParam String strDateTo, @RequestParam Long idEvent) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFrom = LocalDateTime.parse(strDateFrom, formatter);
        LocalDateTime dateTo = LocalDateTime.parse(strDateTo, formatter);

        return ResponseEntity.ok(serviceEvent.listMotionAroundEvent(dateFrom, dateTo, idEvent));
    }
}
