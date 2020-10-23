package com.github.payments.controllers.impl;

import com.github.payments.controllers.IAssetsController;
import com.github.payments.dto.AssetDto;
import com.github.payments.entity.Asset;
import com.github.payments.entity.EntityStatus;
import com.github.payments.service.IAssetsService;
import com.github.payments.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.payments.utils.TransferObj.fromAsset;
import static com.github.payments.utils.TransferObj.toAsset;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/assets")
public class AssetsController implements IAssetsController {

    private final IAssetsService assetsService;

    @Override
    public List<AssetDto> findAssets() {
        return this.assetsService.readByStatus(EntityStatus.on)
                .stream()
                .map(TransferObj::fromAsset)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetDto> findAssetsByStatus(EntityStatus status) {
        return this.assetsService.readByStatus(status)
                .stream()
                .map(TransferObj::fromAsset)
                .collect(Collectors.toList());
    }

    @Override
    public AssetDto findAsset(Long id) {
        return fromAsset(this.assetsService.readById(id));
    }

    @Override
    public AssetDto save(AssetDto payload) {
        return fromAsset(this.assetsService.create(toAsset(payload)));
    }

    @Override
    public void update(AssetDto payload) {
        Asset asset = this.assetsService.readById(payload.getId());
        asset.setOwner(payload.getOwner());
        asset.setName(payload.getName());
        asset.setFullName(payload.getFullName());
        asset.setPow(payload.getPow());
        asset.setAssetType(payload.getAssetType());
        this.assetsService.update(asset);
    }

    @Override
    public void remove(Long id) {
        this.assetsService.remove(id);
    }
}