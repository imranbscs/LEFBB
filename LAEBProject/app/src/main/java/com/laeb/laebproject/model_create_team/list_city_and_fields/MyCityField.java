
package com.laeb.laebproject.model_create_team.list_city_and_fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCityField {

    @SerializedName("field_id")
    @Expose
    private Integer fieldId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
