package com.sd51.fsneakers.features.product.mapper;


import com.sd51.fsneakers.features.product.dto.request.MauSacRequest;
import com.sd51.fsneakers.features.product.dto.response.MauSacResponse;
import com.sd51.fsneakers.features.product.entity.MauSac;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MauSacMapper {

    MauSac toEntity(MauSacRequest req);

    MauSacResponse toResponse(MauSac mauSac);

    void toUpdate(@MappingTarget MauSac mauSac, MauSacRequest req);
}
