package com.sd51.fsneakers.features.product.mapper;

import com.sd51.fsneakers.features.product.dto.request.SanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamResponse;
import com.sd51.fsneakers.features.product.entity.SanPham;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SanPhamMapper {

    SanPham toEntity(SanPhamRequest req);

    SanPhamResponse toResponse(SanPham sanPham);

    void toUpdate(@MappingTarget SanPham sanPham, SanPhamRequest req);
}
