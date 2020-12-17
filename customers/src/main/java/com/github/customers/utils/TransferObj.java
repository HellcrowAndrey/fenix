package com.github.customers.utils;

import com.github.customers.dto.AddressDto;
import com.github.customers.dto.CustomerDto;
import com.github.customers.dto.CustomerStatisticsDto;
import com.github.customers.entity.Address;
import com.github.customers.entity.Customer;
import com.github.customers.entity.CustomerStatistics;

import java.util.Objects;
import java.util.UUID;

public class TransferObj {

    public static Customer toCustomer(CustomerDto data, UUID userId) {
        return new Customer(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                data.getPhone(),
                userId
        );
    }

    public static CustomerDto fromCustomer(Customer data) {
        Address address = data.getAddress();
        return new CustomerDto(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getCustomerEmail(),
                data.getCustomerPhone(),
                Objects.nonNull(address) ? fromAddress(address) : new AddressDto()
        );
    }

    public static Address toAddress(AddressDto data) {
        return new Address(
                data.getId(),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getZipCode()
        );
    }

    public static AddressDto fromAddress(Address data) {
        return new AddressDto(
                data.getId(),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getZipCode()
        );
    }

    public static CustomerStatisticsDto fromCustomerStatistics(CustomerStatistics data) {
        return new CustomerStatisticsDto(
                data.getId(),
                data.getTotalOrders(),
                data.getSuccessOrders(),
                data.getReturnedOrders()
        );
    }

}
