package com.example.common.enums;

public enum LevelEnum {
    HEADER("主管"),
    EMPLOYEE("员工"),
    ;

    public String level;

    LevelEnum(String level) {
        this.level = level;
    }
}
