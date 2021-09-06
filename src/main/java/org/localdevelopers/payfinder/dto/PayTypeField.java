package org.localdevelopers.payfinder.dto;

public enum PayTypeField {

    GAPYUNG("가평군"),
    GOYANG("고양시"),
    GYACHUN("과천시"),
    GYANGMYUNG("광명시"),
    GYANGJU("광주시"),
    GURI("구리시"),
    GUNPO("군포시"),
    GIMPO("김포시"),
    NAMYANGJU("남양주시"),
    DONDUCHUN("동두천시"),
    BOOCHUN("부천시"),
    SUNGNAM("성남시"),
    SUWON("수원시"),
    SIHEUNG("시흥시"),
    ANSAN("안산시"),
    ANSEONG("안성시"),
    ANYANG("안양시"),
    YANGJU("양주시"),
    YANGPYUNG("양평시"),
    YEOJU("여주시");


    private final String name;

    PayTypeField(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
