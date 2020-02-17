package com.moduleTesting.portal.dto;

public enum CarStatus {

    FREE("FREE", 1), BUSY("BUSY", 1);

    private final String name;
    private final int id;

    CarStatus(String name, int id) {
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
