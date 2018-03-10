package net.kprod.aalarmui.controller;

import io.swagger.annotations.ApiOperation;
import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.service.ServiceRemoteImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kemkem on 11/11/17.
 */
@RestController
@RequestMapping("/remote")
public class ControllerRemote {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServiceRemoteImpl serviceRemote;

    @ApiOperation(value = "Get current state")
    @RequestMapping(value = "/state", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCurrentState() throws Exception {
        EnumEventStatus status = serviceRemote.getCurrentStatus();
        return ResponseEntity.ok(status.name());
    }

    @ApiOperation(value = "Toggle state")
    @RequestMapping(value = "/state/toggle", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> toggleState() throws Exception {
        serviceRemote.toggleStatus();
        return ResponseEntity.ok("ok");
    }


}
