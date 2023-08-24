package com.enigma.ICafe.entity.constant;

public enum ECategory {

    REGULAR("reg"),
    VIP("vip"),
    VVVIP("vvvip");
    private final String stringValue;

    ECategory(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

}
