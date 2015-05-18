/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The purpose of this class is to wrapp a DataManagement object to give a
 * Singleton like class using it.
 *
 * @author Oscar
 */
public class SingletonDataManagement {

    private static DataManagement dataManagment;

    // TODO Usar este listener para llamar a todo el Array de Listener
    private static DataListenerImpl dataListener;

    public static DataManagement getDataManagment() {
        return dataManagment;
    }

    public static void setDataManagment(DataManagement dataManagment) {
        SingletonDataManagement.dataManagment = dataManagment;
        SingletonDataManagement.dataListener = new DataListenerImpl();
    }

    public static void addDataListener(DataListener dataListener) {
        SingletonDataManagement.dataListener.addDataListener(dataListener);
    }

    public static void addDataListeners(DataListener[] dataListener) {
        SingletonDataManagement.dataListener.addDataListeners(dataListener);
    }

    public static void addDataListeners(List<DataListener> dataListener) {
        SingletonDataManagement.dataListener.addDataListeners(dataListener);
    }

    /**
     * Recover an Array of Data of the given class with the given where clausule
     *
     * @param where
     * @return An ArrayList&lt;Data&gt; with the recovered Data
     */
    public static ArrayList<Data> recoverData(String where) {

        return dataManagment.recoverData(where);
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryNotCached
     *
     * @param where The where clausule
     */
    public static void recoveryDataAsync(String where) {

        dataManagment.recoverDataAsync(dataListener, where);
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryNotCached
     *
     * @param p The processor
     * @param where The where clausule
     */
    public static void recoveryDataAsync(DataProcessor p, String where) {

        dataManagment.recoverDataAsync(dataListener, p, where);
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryCached if cached is true and
     * handleDataRecoveryNotCached if cached is false
     *
     * @param p The processor
     * @param where The where clausule
     * @param cached True if cached with the table name or no
     */
    public static void recoveryDataAsync(DataProcessor p, String where, boolean cached) {

        dataManagment.recoverDataAsync(dataListener, p, where, where);
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryCached
     *
     * @param p The processor
     * @param where The where clausule
     * @param key The key used to cache the data
     */
    public void recoveryDataAsync(DataProcessor p, String where, String key) {

        dataManagment.recoverDataAsync(dataListener, p, where, true);
    }
    
}

class DataListenerImpl implements DataListener {

    private final ArrayList<DataListener> userDataListeners;

    public DataListenerImpl() {
        userDataListeners = new ArrayList<>();
    }

    @Override
    public Class<? extends Data> getClassforHandle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleDataRecoveryNotCached(ArrayList<Data> data, DataProcessor processor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleDataRecoveryCached(String key, DataProcessor processor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addDataListener(DataListener dataListener) {
        userDataListeners.add(dataListener);
    }

    public void addDataListeners(DataListener[] dataListener) {
        userDataListeners.addAll(Arrays.asList(dataListener));
    }

    public void addDataListeners(List<DataListener> dataListener) {
        userDataListeners.addAll(dataListener);
    }
    
}
