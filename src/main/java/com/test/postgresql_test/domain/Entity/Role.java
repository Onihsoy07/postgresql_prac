package com.test.postgresql_test.domain.Entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin"), MANAGER("manager"), USER("user");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
