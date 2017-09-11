
package com.laeb.laebproject.model_create_team.team_schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("match_type")
    @Expose
    private String matchType;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("team_a")
    @Expose
    private String teamA;
    @SerializedName("team_b")
    @Expose
    private String teamB;
    @SerializedName("team_a_id")
    @Expose
    private Integer teamAId;
    @SerializedName("team_b_id")
    @Expose
    private Integer teamBId;
    @SerializedName("team_a_logo")
    @Expose
    private String teamALogo;
    @SerializedName("team_b_logo")
    @Expose
    private String teamBLogo;
    @SerializedName("referee_id")
    @Expose
    private Integer refereeId;
    @SerializedName("referee")
    @Expose
    private String referee;
    @SerializedName("datetime_from")
    @Expose
    private String datetimeFrom;
    @SerializedName("datetime_to")
    @Expose
    private String datetimeTo;
    @SerializedName("field_name")
    @Expose
    private String fieldName;
    @SerializedName("field_id")
    @Expose
    private Integer fieldId;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("result")
    @Expose
    private Object result;
    @SerializedName("winner")
    @Expose
    private Object winner;
    @SerializedName("approved")
    @Expose
    private Integer approved;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public Integer getTeamAId() {
        return teamAId;
    }

    public void setTeamAId(Integer teamAId) {
        this.teamAId = teamAId;
    }

    public Integer getTeamBId() {
        return teamBId;
    }

    public void setTeamBId(Integer teamBId) {
        this.teamBId = teamBId;
    }

    public String getTeamALogo() {
        return teamALogo;
    }

    public void setTeamALogo(String teamALogo) {
        this.teamALogo = teamALogo;
    }

    public String getTeamBLogo() {
        return teamBLogo;
    }

    public void setTeamBLogo(String teamBLogo) {
        this.teamBLogo = teamBLogo;
    }

    public Integer getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(Integer refereeId) {
        this.refereeId = refereeId;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getDatetimeFrom() {
        return datetimeFrom;
    }

    public void setDatetimeFrom(String datetimeFrom) {
        this.datetimeFrom = datetimeFrom;
    }

    public String getDatetimeTo() {
        return datetimeTo;
    }

    public void setDatetimeTo(String datetimeTo) {
        this.datetimeTo = datetimeTo;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

}
