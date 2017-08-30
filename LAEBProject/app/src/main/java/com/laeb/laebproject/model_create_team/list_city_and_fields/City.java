
package com.laeb.laebproject.model_create_team.list_city_and_fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
