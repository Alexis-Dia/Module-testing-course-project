package com.moduleTesting.portal.dto;

import java.util.Arrays;

public enum UserRole {

    ADMIN("ADMIN", 1), USER("ADMIN", 2);

    private final String name;
    private final int securityLevel;

    UserRole(String name, int securityLevel) {
        this.name = name;
        this.securityLevel = securityLevel;
    }

    public String getName() {
        return name;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public UserRole getRoleByName(String name) {
        return Arrays.stream(values()).filter(ob -> ob.name.equals(name)).findAny().orElseThrow();
    }
}
