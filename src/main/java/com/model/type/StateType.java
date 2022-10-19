package com.model.type;

public enum StateType {
    NEW("새상품"), USED("중고상품");

    private final String description;
    StateType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
