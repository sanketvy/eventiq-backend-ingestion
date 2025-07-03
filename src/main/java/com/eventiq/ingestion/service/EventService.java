package com.eventiq.ingestion.service;

import com.eventiq.ingestion.dto.Event;
import jakarta.servlet.http.HttpServletRequest;

public interface EventService {

    public void processEvent(HttpServletRequest httpRequest, Event event);
}
