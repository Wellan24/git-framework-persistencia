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
 * This class Manage a cache of Data.
 * @author Oscar
 */
public final class DataCache {

    private HashMap<String, ArrayList<Data>> cache;

    public void initializeCache(int initialSize) {

        cache = new HashMap(initialSize);
    }

    /**
     * Construct the cache with a default size of 5
     */
    public DataCache() {

        initializeCache(5);
    }
    /**
     * Construct the cache with initialSize
     * @param initialSize 
     */
    public DataCache(int initialSize) {

        initializeCache(initialSize);
    }

    /**
     * Clear the caché
     */
    public void clearCache() {

        cache.clear();
    }

    /**
     * Returns the cached data in the given key.
     * @param key The key where the data is saved
     * @return 
     */
    public ArrayList<Data> getCachedData(String key) {

        return cache.get(key);
    }

    /**
     * Save the data in the given key.
     * @param key The key where the data will be saved
     */
    void putCacheData(String key, ArrayList<Data> data) {

        cache.put(key, data);
    }
}
