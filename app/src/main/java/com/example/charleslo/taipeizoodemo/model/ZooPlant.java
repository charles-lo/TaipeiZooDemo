package com.example.charleslo.taipeizoodemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 */
public class ZooPlant implements Serializable {

    @SerializedName("F_Location")
    private String m_Location;


    public ZooPlant(String location, String geo, String info, String no, String gategory, String name, String memo, String id, String url) {
        this.m_Location = location;
    }

    public ZooPlant() {

    }
}