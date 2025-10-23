package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.HangGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.HangGiayResponse;
import com.sd51.fsneakers.features.product.entity.HangGiay;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HangGiayMapper {

   HangGiay toEntity(HangGiayRequest req);

    HangGiayResponse toResponse(HangGiay hangGiay);

   void toUpdate(@MappingTarget HangGiay hangGiay, HangGiayRequest req);
}
