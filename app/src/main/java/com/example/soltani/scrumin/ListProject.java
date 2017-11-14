package com.example.soltani.scrumin;

/**
 * Created by Soltani on 13/11/2017.
 */

public class ListProject {

    public String app_name;

    public String app_id;

    public ListProject(String app_name, String app_id) {
        this.app_name = app_name;
        this.app_id = app_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public String getApp_id() {
        return app_id;
    }
}

