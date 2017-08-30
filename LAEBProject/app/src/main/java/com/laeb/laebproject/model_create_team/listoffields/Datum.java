
package com.laeb.laebproject.model_create_team.listoffields;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("myFields")
    @Expose
    private List<MyField> myFields = null;
    @SerializedName("favFields")
    @Expose
    private List<FavField> favFields = null;
    @SerializedName("otherFields")
    @Expose
    private List<OtherField> otherFields = null;

    public List<MyField> getMyFields() {
        return myFields;
    }

    public void setMyFields(List<MyField> myFields) {
        this.myFields = myFields;
    }

    public List<FavField> getFavFields() {
        return favFields;
    }

    public void setFavFields(List<FavField> favFields) {
        this.favFields = favFields;
    }

    public List<OtherField> getOtherFields() {
        return otherFields;
    }

    public void setOtherFields(List<OtherField> otherFields) {
        this.otherFields = otherFields;
    }

}
