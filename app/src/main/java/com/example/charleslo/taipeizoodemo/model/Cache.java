package com.example.charleslo.taipeizoodemo.model;

import java.util.ArrayList;

public class Cache {

    private static Cache intance = null;
    public  ArrayList<ZooSection> m_ZooSectionList;
    public  ArrayList<ZooPlant> m_ZooPlantList;

    public static synchronized Cache getInstance() {
        if (intance == null) {
            intance = new Cache();
        }
        return intance;
    }
}
