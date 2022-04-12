package edu.javagroup.seabattle.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kaa
 * @version 1.0
 */
public interface PulseController {

    @GetMapping
    ResponseEntity<String> pulse();
}
