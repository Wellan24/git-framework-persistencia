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
public class DataCache {

    private static HashMap<String, ArrayList<Data>> cache;

    public static void initializeCache(int initialSize){
        
        if(cache == null)
            cache = new HashMap(initialSize);
    }
    
    public static void clearCache(){
        
        cache.clear();
    }
    
    public static ArrayList<Data> getCacheData(String key) {

        if(cache != null)
            return cache.get(key);
        else
            return null;
    }

    static void putCacheData(String key, ArrayList<Data> data) {

        initializeCache(5);
        cache.put(key, data);
    }
}
