
package com.laeb.laebproject.model_expendible;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wednesday {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("slots")
    @Expose
    private List<Slot> slots = null;

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

}
