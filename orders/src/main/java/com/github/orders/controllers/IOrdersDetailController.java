package com.github.orders.controllers;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IOrdersDetailController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    void createOrder(
            @RequestAttribute(name = "userId") Long userId,
            @RequestBody @Valid OrderDetailDto payload
    );

    @GetMapping(
            path = "/fetch/{status}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<OrderDetail> readAllByStatus(@PathVariable OrderStatus status);

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    OrderDetail readById(@RequestParam(name = "orderId") Long orderId);

    @PutMapping(
            path = "/edit/"
    )
    void update(@RequestBody OrderDetail o);

    @PutMapping(
            path = "/edit/{productId}/{orderStatus}"
    )
    void update(@PathVariable Long productId, @PathVariable OrderStatus orderStatus);

}
