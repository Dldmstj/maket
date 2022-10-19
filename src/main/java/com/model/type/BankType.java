package com.model.type;

public enum BankType {
    IBK("기업은행"), KB("국민은행"), NH("농협은행"), WOORI("우리은행"), SHINHAN("신한은행"),
    HANA("하나은행"), SC("SC은행"), EPOST("우체국") ,KAKAO("카카오뱅크"),
    SINHYEOP("신협"), BUSAN("부산은행"), SUHYEOP("수협"), JB("전북은행"), MG("새마을금고");

    private final String description;
    BankType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
