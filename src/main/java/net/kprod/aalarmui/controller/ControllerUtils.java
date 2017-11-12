package net.kprod.aalarmui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kemkem on 11/11/17.
 */
@RestController
@RequestMapping("/utils")
public class ControllerUtils {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("password/encode")
    public String eventSensor(@RequestParam String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
