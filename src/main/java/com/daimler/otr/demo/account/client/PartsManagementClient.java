package com.daimler.otr.demo.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${parts_management_service.name}", url = "${parts_management_service.url}")
@Profile("!test")
public interface PartsManagementClient {

    @GetMapping("/api/test")
    void test();
}
