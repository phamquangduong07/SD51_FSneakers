package com.sd51.fsneakers.features.order.enums;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus attribute) {
        return (attribute != null) ? attribute.getValue() : null;
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? OrderStatus.fromValue(dbData) : null;
    }
}