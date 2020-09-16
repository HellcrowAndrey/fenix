package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "branches")
    private Set<BranchDto> branches;

    @JsonProperty(value = "cities")
    private Set<String> cities;

    @JsonProperty(value = "homePrice")
    private BigDecimal homePrice;

    @JsonProperty(value = "branchPrice")
    private BigDecimal branchPrice;

}