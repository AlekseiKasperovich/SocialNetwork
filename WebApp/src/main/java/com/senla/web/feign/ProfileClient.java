package com.senla.web.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProfileClient", url = "${request.host}")
public interface ProfileClient {}
