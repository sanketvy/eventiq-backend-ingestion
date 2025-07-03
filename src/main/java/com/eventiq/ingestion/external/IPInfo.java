package com.eventiq.ingestion.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "ipinfo",url = "http://ip-api.com")
public interface IPInfo {

    @GetMapping("/json/{ip}")
    public Map<String, Object> getIpInfo(@PathVariable String ip);
}
