package com.laeb.laebproject.model;

import java.io.Serializable;

/**
 * Created by imrankhan on 8/23/2017.
 */

public class CustomBinder implements Serializable {
    private static final long serialVersionUID = 4466821913603037341L;
    private transient FieldInfo f;


    public FieldInfo getField() {
        return f;
    }

    public void setList(FieldInfo pf) {
        this.f = pf;
    }
}
