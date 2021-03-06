
package com.laeb.laebproject.model_create_team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCities {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("cities")
    @Expose
    private List<City> cities = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}
