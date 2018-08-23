package com.tdd.parkingLot.entities;

import javax.persistence.Entity;

@Entity
public class ParkingLot {
    private String name;
    private int initSize;
    public ParkingLot(String name, int initSize) {
        this.name = name;
        this.initSize = initSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitSize() {
        return initSize;
    }

    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }
}
