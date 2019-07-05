package com.skoczek.jLibrary.domain.enums;

public enum Roles {

    USER_ROLE {
        @Override
        public String toString() {
            return "USER_ROLE";
        }
    },
    ADMIN_ROLE {
        @Override
        public String toString() {
            return "USER_ADMIN";
        }
    }
}
