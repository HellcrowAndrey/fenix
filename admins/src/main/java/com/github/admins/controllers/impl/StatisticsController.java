package com.github.admins.controllers.impl;

import com.github.admins.controllers.IStatisticsController;
import com.github.admins.dto.LoginDto;
import com.github.admins.dto.ViewDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IStatisticsService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController implements IStatisticsController {

    private final IStatisticsService statisticsService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<LoginDto> findLoginsInTime(String start, String end) {
        return this.statisticsService.findLoginsInTime(start, end)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<ViewDto> findViewsInTime(String start, String end) {
        return this.statisticsService.findViewsInTime(start, end)
                .orElseThrow(NotFound::new);
    }
}
