package com.model.type;

public enum CategoryType {
    WOMAN_CLO("여성의류"),MAN_CLO("남성의류"),BAG("가방"),WATCH("시계"),ACC("악세사리"),
    ELECTRO("가전제품"),FUNITURE("가구"),TICKET("티켓"),BOOK("도서/음반"),
    GOODS("굿즈"),FOOD("식품"),BABY("유아"),SHOSES("신발"),ETC("기타");

    private final String description;
    CategoryType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
