package com.portal.springrest.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public enum Role {
    USER(new HashSet<>(Arrays.asList(Permission.ACCOUNT_READ_FILE))),
    MODERATOR(new HashSet<>(Arrays.asList(Permission.ACCOUNT_READ_FILE, Permission.ACCOUNT_UPDATE_FILE))),
    ADMIN(new HashSet<>(Arrays.asList(Permission.ACCOUNT_READ_FILE, Permission.ACCOUNT_UPDATE_FILE,
            Permission.ACCOUNT_READ_ACC, Permission.ACCOUNT_UPDATE_ACC)));

    private Set<Permission> permissions;

     Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
         return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                 .collect(Collectors.toSet());
    }
}
