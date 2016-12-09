package com.albertogiunta.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Stations")
public class Stations {

    @Id
    String id;
    String nameShort;
    String nameLong;
    String stationLongId;
    String stationShortId;

    public Stations() {
    }

    @PersistenceConstructor
    public Stations(String id, String nameShort, String nameLong, String stationLongId, String stationShortId) {
        this.id = id;
        this.nameShort = nameShort;
        this.nameLong = nameLong;
        this.stationLongId = stationLongId;
        this.stationShortId = stationShortId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public String getStationLongId() {
        return stationLongId;
    }

    public void setStationLongId(String stationLongId) {
        this.stationLongId = stationLongId;
    }

    public String getStationShortId() {
        return stationShortId;
    }

    public void setStationShortId(String stationShortId) {
        this.stationShortId = stationShortId;
    }

    @Override
    public String toString() {
        return "Stations{" +
                "id='" + id + '\'' +
                ", nameShort='" + nameShort + '\'' +
                ", nameLong='" + nameLong + '\'' +
                ", stationLongId='" + stationLongId + '\'' +
                ", stationShortId='" + stationShortId + '\'' +
                '}';
    }
}
