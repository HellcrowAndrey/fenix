package com.github.orders.service;

import com.github.orders.dto.BillDto;
import com.github.orders.service.impl.BillService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(
        name = "payments",
        fallback = BillService.class
)
public interface IBillService {

    @GetMapping(
            path = "/v1/bills/fetch"
    )
    Optional<BillDto> findById(@RequestParam(name = "id") Long id);

}
