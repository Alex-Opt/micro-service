package com.ly.mt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RefreshController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RefreshController.class);

    @PostMapping("/actuator/autoRefresh")
    @ResponseBody
    public Object busRefresh2(@RequestBody(required = false) String param) {
        LOGGER.info("auto refresh start:param={}", param);
        return new ModelAndView("/actuator/bus-refresh");
    }
}