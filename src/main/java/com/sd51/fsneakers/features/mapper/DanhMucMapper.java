package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.DanhMucRequest;
import com.sd51.fsneakers.features.product.dto.response.DanhMucResponse;
import com.sd51.fsneakers.features.product.entity.DanhMuc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DanhMucMapper {

    DanhMuc toEntity(DanhMucRequest req);

    DanhMucResponse toResponse(DanhMuc danhMuc);

   void toUpdate(@MappingTarget DanhMuc danhMuc, DanhMucRequest req);
}
