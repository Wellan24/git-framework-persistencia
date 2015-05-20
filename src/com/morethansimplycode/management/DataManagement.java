/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import com.morethansimplycode.data.DataAnnotationUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
    private Connection connection;

    /**
     * The DataCache object that represents the cache.
     */
    private DataCache dataCache;

    /**
     * The DataCache object that represents the cache.
     */
    private DataManagementDatabase dataManagementDatabase;

    private int top = -1;

    /**
     * A contructor using a Connection
     *
     * @param connection the connection to use.
     */
    public DataManagement(Connection connection) {
        try {
            this.connection = connection;
            System.out.println(connection.getMetaData().getDatabaseProductName());
            dataManagementDatabase = DataManagementDatabaseFactory
                    .getDataManagementDatabase(connection.getMetaData().getDatabaseProductName());

        } catch (SQLException ex) {
            Logger.getLogger(DataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a connections using the given parameters.
     *
     * @param className The class name of the driver
     * @param url The url to connect the Database
     */
    public DataManagement(String className, String url) {

        try {

            Class.forName(className);
            this.connection = DriverManager.getConnection(url);
            System.out.println(connection.getMetaData().getDatabaseProductName());
            dataManagementDatabase = DataManagementDatabaseFactory
                    .getDataManagementDatabase(connection.getMetaData().getDatabaseProductName());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a connections using the given parameters
     *
     * @param className The class name of the driver
     * @param url The url to connect the Database
     * @param user The user used or an empty string
     * @param password The password for the user
     */
    public DataManagement(String className, String url, String user, String password) {

        try {

            Class.forName(className);
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection.getMetaData().getDatabaseProductName());
            dataManagementDatabase = DataManagementDatabaseFactory
                    .getDataManagementDatabase(connection.getMetaData().getDatabaseProductName());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DataManagement top(int top) {

        this.top = top;
        return this;
    }

    /**
     * Get the DataManagementDatabase object, used by this class to create the
     * queries. Use it if you want to create it.
     *
     * @return The DataManagementDatabase object.
     */
    public DataManagementDatabase getDataManagementDatabase() {

        return dataManagementDatabase;
    }

    /**
     * Recover an Array of Data of the given class with the given where clausule
     *
     * @param where The where clausule to use
     * @return An ArrayList&lt;Data&gt; with the recovered Data
     */
    public ArrayList<Data> recoverData(Class<? extends Data> d) {

        ArrayList<Data> ret = new ArrayList<>();
        String[] keys = DataAnnotationUtil.recoverDBInfoColumns(d);
        String tableName = DataAnnotationUtil.recoverDBInfoTableName(d);

        try (ResultSet rs = dataManagementDatabase.executeQuery(connection,
                dataManagementDatabase.top(top).createSelectQuery(keys, tableName))) {
            Data data;
            while (rs.next()) {

                data = d.newInstance();
                for (String key : keys)
                    data.put(key, rs.getObject(key));

                ret.add(data);
            }

            top = -1;
            return ret;
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }
    
    /**
     * Recover an Array of Data of the given class with the given where clausule
     *
     * @param where The where clausule to use
     * @return An ArrayList&lt;Data&gt; with the recovered Data
     */
    public ArrayList<Data> recoverData(Class<? extends Data> d, String where) {

        ArrayList<Data> ret = new ArrayList<>();
        String[] keys = DataAnnotationUtil.recoverDBInfoColumns(d);
        String tableName = DataAnnotationUtil.recoverDBInfoTableName(d);

        try (ResultSet rs = dataManagementDatabase.executeQuery(connection,
                dataManagementDatabase.top(top).createSelectQuery(keys, tableName, where))) {
            Data data;
            while (rs.next()) {

                data = d.newInstance();
                for (String key : keys)
                    data.put(key, rs.getObject(key));

                ret.add(data);
            }

            top = -1;
            return ret;
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * gives it to a DataListener, using handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     */
    public void recoverDataAsync(Class<? extends Data> d, DataListener listener) {

    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * gives it to a DataListener, using handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     * @param where The where clausule
     */
    public void recoverDataAsync(Class<? extends Data> d, DataListener listener, String where) {

    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * gives it to a DataListener, using handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     */
    public void recoverDataAsync(Class<? extends Data> d, DataListener listener, DataProcessor p, String where) {

    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * gives it to a DataListener, using handleDataRecoveryCached if cached is
     * true and handleDataRecoveryNotCached if cached is false
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     * @param cached True if cached with the table name or no
     */
    public void recoverDataAsync(Class<? extends Data> d, DataListener listener, DataProcessor p, String where, boolean cached) {

    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * gives it to a DataListener, using handleDataRecoveryCached
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     * @param key The key used to cache the data
     */
    public void recoverDataAsync(Class<? extends Data> d, DataListener listener, DataProcessor p, String where, String key) {

    }

    /**
     * This method add an ArrayList&lt;Data&gt; to the cache, in order to not
     * repeat the connection to the DataBase when it's not needed. It's for
     * internal use only, so that's why it's private.
     *
     * @param key The key used in he Map of the cache.
     * @param data The data to Cache
     */
    private void addDataToCache(String key, ArrayList<Data> data) {

        if (dataCache == null)
            dataCache = new DataCache();

        dataCache.putCacheData(key, data);
    }

    /**
     * Use this method to recover Cached Data saved with the given key.
     *
     * @param key The key of the data in the cache.
     * @return The data saved with that key.
     */
    public ArrayList<Data> getDataFromCache(String key) {

        return dataCache.getCachedData(key);
    }
}
