package com.test.postgresql_test.domain.Entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"), MANAGER("MANAGER"), USER("USER");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
