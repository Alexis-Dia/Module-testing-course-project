package com.moduleTesting.portal.dto;

public enum TaskStatus {

    FREE("FREE", 1), IN_PROGRESS("IN_PROGRESS", 2), VALIDATING("VALIDATING", 3),
    REJECTED("REJECTED", 4), FINISHED("FINISHED", 5), PARTIALLY_FINISHED("PARTIALLY_FINISHED", 6);

    private final String name;
    private final int id;

    TaskStatus(String name, int id) {
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
