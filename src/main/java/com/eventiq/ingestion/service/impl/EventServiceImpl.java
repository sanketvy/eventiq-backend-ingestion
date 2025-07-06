package com.eventiq.ingestion.service.impl;

import com.eventiq.ingestion.constants.Constants;
import com.eventiq.ingestion.external.IPInfo;
import com.eventiq.ingestion.service.EventService;
import com.eventiq.shared.dto.Event;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    StreamBridge streamBridge;

    IPInfo ipInfo;

    public EventServiceImpl(IPInfo ipInfo, StreamBridge streamBridge){
        this.ipInfo = ipInfo;
        this.streamBridge = streamBridge;
    }

    @Override
    public void processEvent(HttpServletRequest httpRequest, Event event) {
        Map<String, Object> data = ipInfo.getIpInfo(httpRequest.getRemoteAddr());
        event.getMetaData().put("location", data.get("city"));
        event.getMetaData().put("country", data.get("country"));
        event.getMetaData().put("lon", data.get("lon"));
        event.getMetaData().put("lat", data.get("lat"));
        event.getMetaData().put("browser", httpRequest.getHeader("User-Agent"));
        streamBridge.send(Constants.EVENTS_TOPIC, MessageBuilder.withPayload(event).setHeader(KafkaHeaders.KEY, event.getProjectId().getBytes(StandardCharsets.UTF_8)).build());
        log.info(event.toString());
    }
}
