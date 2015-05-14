/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.util.ArrayList;

/**
 * The purpose of this class is to wrapp a DataManagement object to give
 * a Singleton like class using it.
 * @author Oscar
 */
public class SingletonDataManagement {

    private static DataManagement dataManagment;
    // TODO Usar este listener para llamar a todo el Array de Listener
    private static DataListener dataListener;

    public static DataManagement getDataManagment() {
        return dataManagment;
    }

    public static void setDataManagment(DataManagement dataManagment) {
        SingletonDataManagement.dataManagment = dataManagment;
    }

    /**
     * Recover an Array of Data of the given class with the given where clausule
     *
     * @param where
     * @return An ArrayList<Data> with the recovered Data
     */
    public static ArrayList<Data> recoverData(String where) {

        return dataManagment.recoverData(where);
    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     * @param where The where clausule
     */
    public static void recoveryDataAsync(String where) {

        dataManagment.recoverDataAsync(dataListener,  where);
    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryNotCached
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     */
    public static void recoveryDataAsync(DataProcessor p, String where) {

        dataManagment.recoverDataAsync(dataListener, p, where);
    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryCached if cached is true and
     * handleDataRecoveryNotCached if cached is false
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     * @param cached True if cached with the table name or no
     */
    public static void recoveryDataAsync(DataProcessor p, String where, boolean cached) {

        dataManagment.recoverDataAsync(dataListener, p, where, where);
    }

    /**
     * This method uses ArrayList<Data> in a separated Thread and then call all
     * the DataListeners of the Class recovered or Data.class, using
     * handleDataRecoveryCached
     *
     * @param listener The listener of the method
     * @param p The processor
     * @param where The where clausule
     * @param key The key used to cache the data
     */
    public void recoveryDataAsync(DataProcessor p, String where, String key) {

        dataManagment.recoverDataAsync(dataListener, p, where, true);
    }

    // Pasar esto a otra clase, o lo anterior
//    
}
