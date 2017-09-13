
package com.laeb.laebproject.model_create_team.recived_invi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("invitation_id")
    @Expose
    private Integer invitationId;
    @SerializedName("invited_by")
    @Expose
    private Integer invitedBy;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("inv_type")
    @Expose
    private String invType;
    @SerializedName("field_id")
    @Expose
    private Integer fieldId;
    @SerializedName("field_name")
    @Expose
    private String fieldName;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("field_price")
    @Expose
    private Integer fieldPrice;
    @SerializedName("field_city")
    @Expose
    private String fieldCity;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("team_id")
    @Expose
    private Integer teamId;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("city")
    @Expose
    private String city;

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(Integer invitedBy) {
        this.invitedBy = invitedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFieldPrice() {
        return fieldPrice;
    }

    public void setFieldPrice(Integer fieldPrice) {
        this.fieldPrice = fieldPrice;
    }

    public String getFieldCity() {
        return fieldCity;
    }

    public void setFieldCity(String fieldCity) {
        this.fieldCity = fieldCity;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
