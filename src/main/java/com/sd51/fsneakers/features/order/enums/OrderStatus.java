package com.sd51.fsneakers.features.order.enums;

import lombok.*;

import lombok.Getter;

@Getter

public enum OrderStatus {

    MOI_TAO(0, "Mới tạo"),
    CHO_THANH_TOAN(1, "Đang chờ thanh toán"),
    DA_THANH_TOAN(2, "Đã thanh toán / Hoàn tất"),
    DA_HUY(3, "Đã hủy"),
    HOAN_TIEN(4, "Hoàn tiền / Trả hàng");

    private final int value;
    private final String description;

    OrderStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value for OrderStatus: " + value);
    }
}