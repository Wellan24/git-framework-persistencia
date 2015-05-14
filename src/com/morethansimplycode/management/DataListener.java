/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.util.ArrayList;

/**
 * This class is used for listen the async data recover.
 * @author Oscar
 */
public interface DataListener {

    /**
     * The class that want to listen. Use Data.class to Handle all.
     * @return The class
     */
    public Class<? extends Data> getClassforHandle();

    /**
     * This method handle the recover of Data that is not Cached.
     * @param data The data recovered.
     * @param processor The processor if there's one.
     */
    public void handleDataRecoveryNotCached(ArrayList<Data> data, DataProcessor processor);

    /**
     * This method handle the recover of Data that is Cached.
     * @param key The key where the Data is cached.
     * @param processor The processor if there's one.
     */
    public void handleDataRecoveryCached(String key, DataProcessor processor);
}
