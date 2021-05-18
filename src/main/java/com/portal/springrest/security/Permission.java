package com.portal.springrest.security;

public enum Permission {
    ACCOUNT_READ_FILE("file.read"),
    ACCOUNT_UPDATE_FILE("file.update"),
    ACCOUNT_READ_ACC("acc.read"),
    ACCOUNT_UPDATE_ACC("acc.update");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
