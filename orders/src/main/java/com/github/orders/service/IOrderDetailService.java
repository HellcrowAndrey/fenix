package com.github.orders.service;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IOrderDetailService {

    OrderDetail crete(OrderDetail o);

    OrderDetail readById(Long orderId);

    Page<OrderDetail> readUserId(UUID userId, Pageable pageable);

    Page<OrderDetail> readByStatus(OrderStatus status, Pageable pageable);

    Page<OrderDetail> readByStaffIdNull(Pageable pageable);

    Page<OrderDetail> readByStaffIdAndStatus(Long staffId, OrderStatus status, Pageable pageable);

    void update(Long id, OrderStatus status);

    void updateOrderManager(Long orderId, Long staffId);

}
