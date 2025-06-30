package com.eventiq.ingestion.controller;

import com.eventiq.ingestion.dto.Event;
import com.eventiq.ingestion.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/v1/public/event")
@Slf4j
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<String> processEvent(@RequestBody Event event, HttpServletRequest httpRequest) throws UnknownHostException {
        log.info(httpRequest.getRemoteHost().toString());
        log.info(httpRequest.getRemoteAddr().toString());
        log.info(event.toString());
        return ResponseEntity.status(HttpStatus.OK).body("Event Processed.");
    }
}
