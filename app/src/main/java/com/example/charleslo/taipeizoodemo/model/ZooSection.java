package com.example.charleslo.taipeizoodemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 */
public class ZooSection implements Serializable {

    @SerializedName("E_Pic_URL")
    public String m_PicUrl;
    @SerializedName("E_Geo")
    private String m_Geo;
    @SerializedName("E_Info")
    public String m_Info;
    @SerializedName("E_no")
    private String m_no;
    @SerializedName("E_Category")
    public String m_Category;
    @SerializedName("E_Name")
    public String m_Name;
    @SerializedName("E_Memo")
    public String m_Memo;
    @SerializedName("_id")
    private String m_Id;
    @SerializedName("E_URL")
    public String m_URL;

    public ZooSection(String picUrl, String geo, String info, String no, String gategory, String name, String memo, String id, String url) {
        this.m_PicUrl = picUrl;
        this.m_Geo = geo;
        this.m_Info = info;
        this.m_no = no;
        this.m_Category = gategory;
        this.m_Name = name;
        this.m_Memo = memo;
        this.m_Id = id;
        this.m_URL = url;
    }

    public ZooSection() {

    }
}