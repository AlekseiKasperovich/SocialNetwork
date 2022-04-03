package com.senla.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "feignClient", url = "${request.host}")
public interface FeignServiceUtil {

    @GetMapping(value = "/", consumes = "application/json", produces = "application/json")
    String go();

}
