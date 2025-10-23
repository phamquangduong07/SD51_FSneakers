package com.sd51.fsneakers.features.mapper;


import com.sd51.fsneakers.features.product.dto.request.ChatLieuRequest;
import com.sd51.fsneakers.features.product.dto.response.ChatLieuResponse;
import com.sd51.fsneakers.features.product.entity.ChatLieu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChatLieuMapper {

    ChatLieu toEntity(ChatLieuRequest request);

    ChatLieuResponse toResponse(ChatLieu chatLieu);

    void toUpdate(@MappingTarget ChatLieu chatLieu, ChatLieuRequest req);

}
