package com.sd51.fsneakers.features.order.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ThanhToanMethodConverter implements AttributeConverter<ThanhToanMethod, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ThanhToanMethod attribute) {
        return (attribute != null) ? attribute.getValue() : null;
    }

    @Override
    public ThanhToanMethod convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? ThanhToanMethod.fromValue(dbData) : null;
    }
}
