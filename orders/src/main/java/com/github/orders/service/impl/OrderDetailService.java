package com.github.orders.service.impl;

import com.github.orders.entity.OrderDetail;
import com.github.orders.entity.OrderStatus;
import com.github.orders.exceptions.BadRequest;
import com.github.orders.exceptions.NotFound;
import com.github.orders.exceptions.TypeMessage;
import com.github.orders.repository.OrderDetailRepo;
import com.github.orders.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"order", "orders"})
public class OrderDetailService implements IOrderDetailService {

    private final OrderDetailRepo orderRepo;

    @Override
    @Caching(
            put = @CachePut(value = "order", key = "#o.id"),
            evict = @CacheEvict(value = "orders", allEntries = true)
    )
    public OrderDetail crete(OrderDetail o) {
         return this.orderRepo.save(o);
    }

    @Override
    @Cacheable(value = "order", key = "#orderId")
    public OrderDetail readById(Long orderId) {
        return this.orderRepo.findById(orderId)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Cacheable(value = "order", key = "#userId")
    public OrderDetail readUserId(Long userId) {
        return this.orderRepo.findByUserId(userId)
                .orElseThrow(() -> new BadRequest(
                        TypeMessage.orderFindError)
                );
    }

    @Override
    @Cacheable(value = "orders", key = "#userId", unless = "#result.size() == 0")
    public List<OrderDetail> readAllUserId(Long userId) {
        return this.orderRepo.findAllByUserId(userId);
    }

    @Override
    @Cacheable(value = "orders", key = "#status", unless = "#result.size() == 0")
    public List<OrderDetail> readByStatus(OrderStatus status) {
        return this.orderRepo.findByStatus(status);
    }

    @Override
    @Caching(
            put = @CachePut(value = "order", key = "#id"),
            evict = @CacheEvict(value = "orders", allEntries = true)
    )
    public void update(Long id, OrderStatus status) {
        this.orderRepo.updateOrderByStatus(id, status);
    }

    @Override
    @Caching(
            put = @CachePut(value = "order", key = "#o.id"),
            evict = @CacheEvict(value = "orders", allEntries = true)
    )
    public void update(OrderDetail o) {
        this.orderRepo.save(o);
    }

}
