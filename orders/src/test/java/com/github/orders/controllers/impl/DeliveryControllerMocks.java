package com.github.orders.controllers.impl;

import com.github.orders.dto.DeliveryDto;
import com.github.orders.entity.Delivery;
import com.github.orders.entity.DeliveryType;

import java.math.BigDecimal;
import java.util.UUID;

public class DeliveryControllerMocks {

    public static final UUID USER_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static Delivery deliveryForSave() {
        return new Delivery(
                null,
                DeliveryType.home,
                "Novaposhta",
                "Burbonova street 23",
                BigDecimal.TEN,
                USER_ID
        );
    }

    public static DeliveryDto deliveryForEquals() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Novaposhta",
                "Burbonova street 23",
                new BigDecimal("10.0000")
        );
    }

    public static DeliveryDto deliveryForUpdate() {
        return new DeliveryDto(
                1L,
                DeliveryType.home,
                "Misexpres",
                "Burbonova street 23",
                new BigDecimal("10.0000")
        );
    }

}