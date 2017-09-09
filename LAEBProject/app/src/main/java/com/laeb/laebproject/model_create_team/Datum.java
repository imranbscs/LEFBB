
package com.laeb.laebproject.model_create_team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public String getFcLocal() {
        return fcLocal;
    }

    public void setFcLocal(String fcLocal) {
        this.fcLocal = fcLocal;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fc_local")
    @Expose
    private String fcLocal;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @SerializedName("player")
    @Expose
    private int player;

    public String getFcInternational() {
        return fcInternational;
    }

    public void setFcInternational(String fcInternational) {
        this.fcInternational = fcInternational;
    }

    @SerializedName("fc_international")
    @Expose
    private String fcInternational;
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @SerializedName("district")
    @Expose
    private String district;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @SerializedName("nickname")
    @Expose
    private String nick;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("stars")
    @Expose
    private Object stars;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("playing_role")
    @Expose
    private String playingRole;
    @SerializedName("weight")
    @Expose
    private Object weight;
    @SerializedName("height")
    @Expose
    private Object height;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("age")
    @Expose
    private Age age;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Object getStars() {
        return stars;
    }

    public void setStars(Object stars) {
        this.stars = stars;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlayingRole() {
        return playingRole;
    }

    public void setPlayingRole(String playingRole) {
        this.playingRole = playingRole;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

}
