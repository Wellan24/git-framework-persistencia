/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manage a connection to a DataBase and its uses.
 *
 * @author Oscar
 */
public class DataManagement {

    /**
     * The Connection used to connect to the required DataBase.
     */
    private Connection conection;

    /**
     * The DataCache object that represents the cache.
     */
    private DataCache dataCache;

    /**
     * A contructor using a Connection
     *
     * @param conection
     */
    public DataManagement(Connection conection) {
        this.conection = conection;
    }

    /**
     * A contructor using a Connection
     *
     * @param conection
     */
    public DataManagement(String className, String url, String user, String password) {

        try {

            Class.forName(className);
            this.conection = DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Data> recuperarDatos(String where) {

        return null;
    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then gives it
     * to a DataListener, using handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     * @param where The where clausule
     */
    public void recoveryDataAsync(DataListener listener) {

    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then gives it
     * to a DataListener, using handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     * @param where The where clausule
     */
    public void recoveryDataAsync(DataListener listener, String where) {

    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then gives it
     * to a DataListener, using handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     */
    public void recoveryDataAsync(DataListener listener, DataProcessor p, String where) {

    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then gives it
     * to a DataListener, using handleDataRecoveryCached if cached is true and
     * handleDataRecoveryNotCached if cached is false
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     * @param cached True if cached with the table name or no
     */
    public void recoveryDataAsync(DataListener listener, DataProcessor p, String where, boolean cached) {

    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then gives it
     * to a DataListener, using handleDataRecoveryCached
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     * @param key The key used to cache the data
     */
    public void recoveryDataAsync(DataListener listener, DataProcessor p, String where, String key) {

    }

    /**
     * This method add an ArrayList<Data> to the cache, in order to not repeat
     * the connection to the DataBase when it's not needed. It's for internal
     * use only, so that's why it's private.
     *
     * @param key The key used in he Map of the cache.
     * @param data
     */
    private void addDataToCache(String key, ArrayList<Data> data) {

        if (dataCache == null)
            dataCache = new DataCache();

        dataCache.putCacheData(key, data);
    }

    /**
     * Use this method to recover Cached Data saved with the given key.
     *
     * @param key
     * @return
     */
    public ArrayList<Data> getDataFromCache(String key) {

        return dataCache.getCachedData(key);
    }
}
