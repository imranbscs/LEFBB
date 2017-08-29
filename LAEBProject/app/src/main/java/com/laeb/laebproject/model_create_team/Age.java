
package com.laeb.laebproject.model_create_team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Age {

    @SerializedName("years")
    @Expose
    private Integer years;
    @SerializedName("days")
    @Expose
    private Integer days;

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

}
