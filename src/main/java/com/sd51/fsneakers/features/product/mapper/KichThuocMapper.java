package com.sd51.fsneakers.features.product.mapper;


import com.sd51.fsneakers.features.product.dto.request.KichThuocRequest;
import com.sd51.fsneakers.features.product.dto.response.KichThuocResponse;
import com.sd51.fsneakers.features.product.entity.KichThuoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KichThuocMapper {

    KichThuoc toEntity(KichThuocRequest req);

    KichThuocResponse toResponse(KichThuoc kichThuoc);

    void toUpdate(@MappingTarget KichThuoc kichThuoc, KichThuocRequest req);
}
