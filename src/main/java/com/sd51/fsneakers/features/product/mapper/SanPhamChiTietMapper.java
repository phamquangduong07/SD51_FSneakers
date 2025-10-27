package com.sd51.fsneakers.features.product.mapper;

import com.sd51.fsneakers.features.product.dto.request.SanPhamChiTietRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamChiTietResponse;
import com.sd51.fsneakers.features.product.entity.*;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SanPhamChiTietMapper {

    // Entity -> Request
    @Mappings({
            @Mapping(target = "chatLieu.id", source = "chatLieuId"),
            @Mapping(target = "danhMuc.id", source = "danhMucId"),
            @Mapping(target = "deGiay.id", source = "deGiayId"),
            @Mapping(target = "hangGiay.id", source = "hangGiayId"),
            @Mapping(target = "kichThuoc.id", source = "kichThuocId"),
            @Mapping(target = "mauSac.id", source = "mauSacId"),
            @Mapping(target = "sanPham.id", source = "sanPhamId"),
            @Mapping(target = "hinhAnhSanPhams", source = "hinhAnhSanPhamUrls", qualifiedByName = "urlsToImages")
    })
    SanPhamChiTiet toEntity(SanPhamChiTietRequest req);

    // Response -> Entity
    @Mappings({
            @Mapping(target = "chatLieu", source = "chatLieu.id"),
            @Mapping(target = "danhMuc", source = "danhMuc.id"),
            @Mapping(target = "deGiay", source = "deGiay.id"),
            @Mapping(target = "hangGiay", source = "hangGiay.id"),
            @Mapping(target = "kichThuoc", source = "kichThuoc.id"),
            @Mapping(target = "mauSac", source = "mauSac.id"),
            @Mapping(target = "sanPham", source = "sanPham.id"),
            @Mapping(target = "hinhAnhSanPhamUrls", source = "hinhAnhSanPhams", qualifiedByName = "imagesToUrls")
    })
    SanPhamChiTietResponse toResponse(SanPhamChiTiet entity);

    void toUpdate(@MappingTarget SanPhamChiTiet entity, SanPhamChiTietRequest req);

    // Custom mapper
    @Named("urlsToImages")
    default List<HinhAnhSanPham> mapUrlsToImages(List<String> urls) {
        if (urls == null) return null;
        return urls.stream()
                .map(url -> HinhAnhSanPham.builder()
                        .url(url)
                        .trangThai(1)
                        .build())
                .collect(Collectors.toList());
    }

    @Named("imagesToUrls")
    default List<String> mapImagesToUrls(List<HinhAnhSanPham> images) {
        if (images == null) return null;
        return images.stream()
                .map(HinhAnhSanPham::getUrl)
                .collect(Collectors.toList());
    }


}
