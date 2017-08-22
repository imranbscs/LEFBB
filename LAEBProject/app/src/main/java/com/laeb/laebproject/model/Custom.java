package com.laeb.laebproject.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by imrankhan on 8/22/2017.
 */

public class Custom implements Serializable {

    private static final long serialVersionUID = 4466821913603037341L;
    private HashMap<String, String> list;


    public HashMap<String, String> getList() {
        return list;
    }

    public void setList(HashMap<String, String> list) {
        this.list = list;
    }
}

