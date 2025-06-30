package com.eventiq.ingestion.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    private String iq_project_id;

    private String type;

    private Map<String, Object> metaData;
}
