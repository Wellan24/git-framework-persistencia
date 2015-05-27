/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.util.HashMap;

/**
 *
 * @author Oscar Date 28-may-2015 Time 1:17:50
 */
public class DataSearch {

    private Class<? extends Data> dataClass;
    private HashMap<String, String> searches;
    private HashMap<String, String> sql;

    public DataSearch(Class<? extends Data> dataClass) {

        this.dataClass = dataClass;
        this.searches = new HashMap<>();
    }

    public void addSearch(String key, String value) {

        searches.put(key, value);
    }

    public void addAndSQL(String key, String value) {

        sql.put(key, " and " + value);
    }

    public void addOrSQL(String key, String value) {

        sql.put(key, " or " + value);
    }

    public String generarConsulta(){
        
        StringBuilder dev = new StringBuilder("Select ");
        return null;
    }
    
}
