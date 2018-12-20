package com.example.charleslo.taipeizoodemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 */
public class ZooPlant implements Serializable {

    @SerializedName("F_Location")
    public String m_Location;
    @SerializedName("F_Name_Ch")
    public String m_NameCh;
    @SerializedName("F_Name_Latin")
    public String m_NameLatin;
    @SerializedName("F_AlsoKnown")
    public String m_AlsoKnow;
    @SerializedName("F_Brief")
    public String m_Brief;
    @SerializedName("F_Feature")
    public String m_Feature;
    @SerializedName("F_Function&Application")
    public String m_FunctionApplication;
    @SerializedName("F_Pic01_URL")
    public String m_Pic01URL;
    @SerializedName("F_Update")
    public String m_Update;

    public ZooPlant(String location, String nameCh, String alsoKnow, String pic01URL, String nameLatin, String brief, String feature, String functionApplication, String update) {
        this.m_Location = location;
        this.m_NameCh = nameCh;
        this.m_AlsoKnow = alsoKnow;
        this.m_Pic01URL = pic01URL;
        this.m_NameLatin = nameLatin;
        this.m_Brief = brief;
        this.m_Feature = feature;
        this.m_FunctionApplication = functionApplication;
        this.m_Update = update;
    }

    public ZooPlant() {

    }
}