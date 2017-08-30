
package com.laeb.laebproject.booking_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slot_id")
    @Expose
    private Integer slotId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("city")
    @Expose
    private String city;
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
    @SerializedName("water")
    @Expose
    private Integer water;
    @SerializedName("floodlights")
    @Expose
    private Integer floodlights;
    @SerializedName("change_room")
    @Expose
    private Integer changeRoom;
    @SerializedName("parking")
    @Expose
    private Integer parking;
    @SerializedName("closed")
    @Expose
    private Integer closed;
    @SerializedName("football")
    @Expose
    private Object football;
    @SerializedName("vests")
    @Expose
    private Object vests;
    @SerializedName("wc")
    @Expose
    private Object wc;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("stars")
    @Expose
    private Integer stars;

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

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
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

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public Object getFootball() {
        return football;
    }

    public void setFootball(Object football) {
        this.football = football;
    }

    public Object getVests() {
        return vests;
    }

    public void setVests(Object vests) {
        this.vests = vests;
    }

    public Object getWc() {
        return wc;
    }

    public void setWc(Object wc) {
        this.wc = wc;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

}
