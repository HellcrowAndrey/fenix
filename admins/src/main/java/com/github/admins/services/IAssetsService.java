package com.github.admins.services;

import com.github.admins.dto.AssetDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.impl.AssetsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "payments",
        fallback = AssetsService.class,
        contextId = "assetsId"
)
public interface IAssetsService {

    @GetMapping(
            path = "/v1/fetch/all/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Optional<List<AssetDto>> findAssetsByStatus(
            @PathVariable(name = "status") EntityStatus status
    );

    @GetMapping(
            path = "/v1/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Optional<AssetDto> findAsset(@PathVariable(name = "id") Long id);

    @PostMapping(
            path = "/v1/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    Optional<AssetDto> save(@RequestBody AssetDto payload);

    @PutMapping(
            path = "/v1/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody AssetDto payload);

    @DeleteMapping(
            path = "/v1/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
