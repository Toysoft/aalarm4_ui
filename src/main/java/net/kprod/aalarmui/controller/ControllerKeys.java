package net.kprod.aalarmui.controller;

import io.swagger.annotations.ApiOperation;
import net.kprod.aalarmui.bean.NfcKey;
import net.kprod.aalarmui.service.ServiceNfcKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kemkem on 11/11/17.
 */
@RestController
@RequestMapping("/keys")
public class ControllerKeys {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServiceNfcKey serviceNfcKey;

    @ApiOperation(value = "Report key register")
    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> register(@RequestParam String uid) throws Exception {
        LOG.debug("Register new uid " + uid);
        serviceNfcKey.registerKey(uid);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "Check key validity")
    @RequestMapping(value = "/verify", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> verify(@RequestParam String uid) throws Exception {
        return ResponseEntity.ok(serviceNfcKey.verifyKey(uid) ? "accepted" : "rejected");
    }

    @ApiOperation(value = "Set key name")
    @RequestMapping(value = "/name", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> name(@RequestParam String uid, @RequestParam String name) throws Exception {
        serviceNfcKey.setKeyName(uid, name);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "List keys")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NfcKey>> list() throws Exception {
        return ResponseEntity.ok(serviceNfcKey.listKey());
    }

}
