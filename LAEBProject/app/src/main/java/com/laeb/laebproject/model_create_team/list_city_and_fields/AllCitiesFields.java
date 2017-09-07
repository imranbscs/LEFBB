
package com.laeb.laebproject.model_create_team.list_city_and_fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCitiesFields {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("cities")
    @Expose
    private List<City> cities = null;
    @SerializedName("myCityFields")
    @Expose
    private List<MyCityField> myCityFields = null;

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

    public List<MyCityField> getMyCityFields() {
        return myCityFields;
    }

    public void setMyCityFields(List<MyCityField> myCityFields) {
        this.myCityFields = myCityFields;
    }

}
