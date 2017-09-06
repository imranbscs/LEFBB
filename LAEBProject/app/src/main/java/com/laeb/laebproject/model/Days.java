package com.laeb.laebproject.model;

/**
 * Created by imrankhan on 8/22/2017.
 */

public enum Days {
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7);

    private int numVal;

    Days(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    }
