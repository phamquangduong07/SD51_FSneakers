package com.sd51.fsneakers.features.mapper;

import com.sd51.fsneakers.features.product.dto.request.HinhAnhSanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.HinhAnhSanPhamResponse;
import com.sd51.fsneakers.features.product.entity.HinhAnhSanPham;
import com.sd51.fsneakers.features.product.entity.SanPhamChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HinhAnhSanPhamMapper {

    // Entity -> Request
    @Mapping(target = "chiTietSanPham.id", source = "chiTietSanPhamId")
    HinhAnhSanPham toEntity(HinhAnhSanPhamRequest req);

    // Response -> Entity
    @Mapping(target = "chiTietSanPham", source = "chiTietSanPham.id")
    HinhAnhSanPhamResponse toResponse(HinhAnhSanPham hinhAnhSanPham);

    void toUpdate(@MappingTarget HinhAnhSanPham existing, HinhAnhSanPhamRequest req);
}
