package com.telefonica.superhero.domain.enums;

import lombok.Data;

public enum RaceDataEnum {

    HOUR(0),
    SUPER_HERO(1),
    BACK_NUMBER(2),
    LapTime(3),
    AVERAGE_LAP_SPEED(4);

    private final int key;

    RaceDataEnum(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
