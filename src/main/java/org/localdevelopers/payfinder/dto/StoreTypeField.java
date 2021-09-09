package org.localdevelopers.payfinder.dto;

import com.mongodb.lang.Nullable;

public enum StoreTypeField {
    RESTAURANT("음식" ),
    CLOTHES("의류"),
    LODGE("숙박"),
    MART("유통"),
    DRINK("음료"),
    ClINIC("보건"),
    GASSTATION("연료");

    private final String name;


    StoreTypeField(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
