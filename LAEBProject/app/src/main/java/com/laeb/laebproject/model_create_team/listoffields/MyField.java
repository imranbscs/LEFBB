
package com.laeb.laebproject.model_create_team.listoffields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyField {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("floodlights")
    @Expose
    private Integer floodlights;
    @SerializedName("change_room")
    @Expose
    private Integer changeRoom;
    @SerializedName("parking")
    @Expose
    private Integer parking;
    @SerializedName("wifi")
    @Expose
    private Integer wifi;
    @SerializedName("closed")
    @Expose
    private Integer closed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFloodlights() {
        return floodlights;
    }

    public void setFloodlights(Integer floodlights) {
        this.floodlights = floodlights;
    }

    public Integer getChangeRoom() {
        return changeRoom;
    }

    public void setChangeRoom(Integer changeRoom) {
        this.changeRoom = changeRoom;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public Integer getWifi() {
        return wifi;
    }

    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

}
