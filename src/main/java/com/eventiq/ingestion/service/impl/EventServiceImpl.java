package com.eventiq.ingestion.service.impl;

import com.eventiq.ingestion.dto.Event;
import com.eventiq.ingestion.external.IPInfo;
import com.eventiq.ingestion.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    IPInfo ipInfo;

    public EventServiceImpl(IPInfo ipInfo){
        this.ipInfo = ipInfo;
    }

    @Override
    public void processEvent(HttpServletRequest httpRequest, Event event) {
        Map<String, Object> data = ipInfo.getIpInfo(httpRequest.getRemoteAddr().toString());
        event.getMetaData().put("location", data.get("city"));
        event.getMetaData().put("country", data.get("country"));
        event.getMetaData().put("lon", data.get("lon"));
        event.getMetaData().put("lat", data.get("lat"));
        event.getMetaData().put("browser", httpRequest.getHeader("User-Agent").toString());
        log.info(event.toString());
    }
}
