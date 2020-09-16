package com.github.admins.controllers.impl;

import com.github.admins.controllers.IDeliveryCompanyController;
import com.github.admins.dto.CompanyDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IDeliveryCompaniesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.admins.utils.TransferObj.fromCompany;
import static com.github.admins.utils.TransferObj.toCompany;

@RestController
@RequestMapping(path = "/v1/delivery/company")
@RequiredArgsConstructor
public class DeliveryCompanyController implements IDeliveryCompanyController {

    private final IDeliveryCompaniesService deliveryCompaniesService;

    @Override
    public CompanyDto save(CompanyDto payload) {
        return fromCompany(
                this.deliveryCompaniesService.save(toCompany(payload))
                        .orElseThrow(BadRequest::new)
        );
    }

    @Override
    public CompanyDto findById(Long id) {
        return fromCompany(
                this.deliveryCompaniesService.findById(id)
                        .orElseThrow(NotFound::new)
        );
    }

    @Override
    public void update(CompanyDto payload) {
        this.deliveryCompaniesService.update(toCompany(payload));
    }

    @Override
    public void delete(Long id) {
        this.deliveryCompaniesService.delete(id);
    }
}