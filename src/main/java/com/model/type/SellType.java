package com.model.type;

public enum SellType {
    JIC("직거래"), POST("택배거래"), ALL("모두가능");

    private final String description;
    SellType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
