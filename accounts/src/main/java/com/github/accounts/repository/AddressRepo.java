package com.github.accounts.repository;

import com.github.accounts.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    @Modifying
    @Query(value = "update Address a set a.country=:country, a.city=:city, a.street=:street," +
            " a.streetNumber=:streetNumber, a.flatNumber=:flatNumber, a.state=:state, a.zipCode=:zipCode where a.id=:id")
    void update(
            @Param(value = "id") Long id,
            @Param(value = "country") String country,
            @Param(value = "city") String city,
            @Param(value = "street") String street,
            @Param(value = "streetNumber") Integer streetNumber,
            @Param(value = "flatNumber") Integer flatNumber,
            @Param(value = "state") String state,
            @Param(value = "zipCode") Integer zipCode
    );

}
