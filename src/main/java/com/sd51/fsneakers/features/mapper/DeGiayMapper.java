package com.sd51.fsneakers.features.mapper;

import com.sd51.fsneakers.features.product.dto.request.DeGiayRequest;
import com.sd51.fsneakers.features.product.dto.response.DeGiayResponse;
import com.sd51.fsneakers.features.product.entity.DeGiay;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DeGiayMapper {

    DeGiay toEntity(DeGiayRequest req);

    DeGiayResponse toResponse(DeGiay deGiay);

    void toUpdate(@MappingTarget DeGiay deGiay, DeGiayRequest req);
}
