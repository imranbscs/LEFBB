package com.laeb.laebproject.model;

/**
 * Created by imrankhan on 8/22/2017.
 */

public enum PlayerPosition {
    GoalKeeper(1),
    Defender(2) ,
    Midfielder(3) ,
    Striker(4);

    private int numVal;

    PlayerPosition(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    }
