package com.sd51.fsneakers.features.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public enum ThanhToanMethod{
        TIEN_MAT(0, "Tiền mặt"),
        CHUYEN_KHOAN(1, "Chuyển khoản"),
        KET_HOP(2, "Kết hợp"),
        VNPAY(3, "VNPay");

    private final int value;
    private final String description;

    ThanhToanMethod(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static ThanhToanMethod fromValue(int value) {
        for (ThanhToanMethod status : ThanhToanMethod.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value for ThanhToanMethod: " + value);
    }

}
