package com.eventiq.ingestion.controller;

import com.eventiq.ingestion.dto.Event;
import com.eventiq.ingestion.external.IPInfo;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/event")
@Slf4j
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService, IPInfo ipInfo){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<String> processEvent(HttpServletRequest httpRequest, @RequestBody Event event) throws UnknownHostException {
        eventService.processEvent(httpRequest, event);
        return ResponseEntity.status(HttpStatus.OK).body("Event Processed.");
    }
}
