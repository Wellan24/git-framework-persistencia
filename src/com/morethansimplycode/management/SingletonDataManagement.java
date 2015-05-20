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

    private DataManagement dataManagment;

    private DataListenerImpl dataListener;

    public static SingletonDataManagement instance;

    private SingletonDataManagement() {

    }

    public static SingletonDataManagement getInstance() {

        if (instance == null)
            instance = new SingletonDataManagement();

        return instance;
    }

    public DataManagement getDataManagment() {
        return dataManagment;
    }

    public void setDataManagment(DataManagement dataManagment) {
        this.dataManagment = dataManagment;
        this.dataListener = new DataListenerImpl();
    }

    public void addDataListener(DataListener dataListener) {
        this.dataListener.addDataListener(dataListener);
    }

    public void addDataListeners(DataListener[] dataListener) {
        this.dataListener.addDataListeners(dataListener);
    }

    public void addDataListeners(List<DataListener> dataListener) {
        this.dataListener.addDataListeners(dataListener);
    }

    /**
     * Recover an Array of Data of the given class with the given where clausule
     *
     * @param where
     * @return An ArrayList&lt;Data&gt; with the recovered Data
     */
    public ArrayList<Data> recoverData(Class<? extends Data> d) {

        return dataManagment.recoverData(d);
    }
    
    /**
     * Recover an Array of Data of the given class with the given where clausule
     *
     * @param where
     * @return An ArrayList&lt;Data&gt; with the recovered Data
     */
    public ArrayList<Data> recoverData(Class<? extends Data> d, String where) {

        return dataManagment.recoverData(d, where);
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * call all the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryNotCached
     *
     * @param where The where clausule
     */
    public SingletonDataManagement recoveryDataAsync(Class<? extends Data> d, String where) {

        dataManagment.recoverDataAsync(d, dataListener, where);
        return this;
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * call all the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryNotCached
     *
     * @param p The processor
     * @param where The where clausule
     */
    public SingletonDataManagement recoveryDataAsync(Class<? extends Data> d, DataProcessor p, String where) {

        dataManagment.recoverDataAsync(d, dataListener, p, where);
        return this;
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * call all the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryCached if cached is true and
     * handleDataRecoveryNotCached if cached is false
     *
     * @param p The processor
     * @param where The where clausule
     * @param cached True if cached with the table name or no
     */
    public SingletonDataManagement recoveryDataAsync(Class<? extends Data> d, DataProcessor p, String where, boolean cached) {

        dataManagment.recoverDataAsync(d, dataListener, p, where, where);
        return this;
    }

    /**
     * This method uses ArrayList&lt;Data&gt; in a separated Thread and then
     * call all the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryCached
     *
     * @param p The processor
     * @param where The where clausule
     * @param key The key used to cache the data
     */
    public SingletonDataManagement recoveryDataAsync(Class<? extends Data> d, DataProcessor p, String where, String key) {

        dataManagment.recoverDataAsync(d, dataListener, p, where, true);
        return this;
    }

    public SingletonDataManagement top(int top) {

        dataManagment.top(top);
        return this;
    }

}

class DataListenerImpl implements DataListener {

    private final ArrayList<DataListener> userDataListeners;
    private final Class<? extends Data> listenedDataClass = Data.class;

    private Class<? extends Data> listeningDataClass = Data.class;

    public DataListenerImpl() {
        userDataListeners = new ArrayList<>();
    }

    @Override
    public Class<? extends Data> getClassforHandle() {

        return listenedDataClass;
    }

    @Override
    public boolean isListeningClass(Class<? extends Data> dataClass) {

        listeningDataClass = dataClass;
        return dataClass.equals(listenedDataClass);
    }

    @Override
    public void handleDataRecoveryNotCached(ArrayList<Data> data, DataProcessor processor) {

        userDataListeners.stream().filter((dl) -> (dl.isListeningClass(listeningDataClass))).forEach((dl) -> {
            dl.handleDataRecoveryNotCached(data, processor);
        });
    }

    @Override
    public void handleDataRecoveryCached(String key, DataProcessor processor) {

        userDataListeners.stream().filter((dl) -> (dl.isListeningClass(listeningDataClass))).forEach((dl) -> {
            dl.handleDataRecoveryCached(key, processor);
        });
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
