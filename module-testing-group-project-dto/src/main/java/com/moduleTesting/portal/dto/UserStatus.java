package com.moduleTesting.portal.dto;

public enum UserStatus {

    FREE("FREE", 1), BUSY("BUSY", 2);

    private final String name;
    private final int id;

    UserStatus(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
