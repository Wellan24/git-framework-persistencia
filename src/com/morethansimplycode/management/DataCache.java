/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Oscar
 */
public final class DataCache {

    private HashMap<String, ArrayList<Data>> cache;

    public void initializeCache(int initialSize) {

        cache = new HashMap(initialSize);
    }

    public DataCache() {

        initializeCache(5);
    }

    public DataCache(int initialSize) {

        initializeCache(initialSize);
    }

    public void clearCache() {

        cache.clear();
    }

    public ArrayList<Data> getCacheData(String key) {

        return cache.get(key);
    }

    void putCacheData(String key, ArrayList<Data> data) {

        cache.put(key, data);
    }
}
