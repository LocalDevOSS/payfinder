package org.localdevelopers.payfinder.dto;

public enum StoreTypeField {
    RESTAURANT("일반휴게음식"),
    CLOTHES("의류"),
    LODGE("숙박업"),
    MART("유통업"),
    DRINK("음료식품"),
    ClINIC("의원"),
    GASSTATION("연료판매점");

    private final String name;

    StoreTypeField(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
