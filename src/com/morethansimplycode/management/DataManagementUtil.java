/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class DataManagementUtil {
    
    private static DataManagement dataManagment;
    // TODO Usar este listener para llamar a todo el Array de Listener
    private static DataListener dataListener;
    
    public static DataManagement getDataManagment() {
        return dataManagment;
    }
    
    public static void setDataManagment(DataManagement dataManagment) {
        DataManagementUtil.dataManagment = dataManagment;
    }    
    
    public static ArrayList<Data> recuperarDatos(String where) {
        
        return dataManagment.recuperarDatos(where);
    }
    
    public static void recoveryDataAsync(DataProcessor p, String where) {
        
        dataManagment.recoveryDataAsync(dataListener, p, where);
    }
    
    public static void recoveryDataAsync(DataProcessor p, String where, boolean cached) {
        
        dataManagment.recoveryDataAsync(dataListener, p, where, where);
    }
    
    public void recoveryDataAsync(DataProcessor p, String where, String key) {
        
        dataManagment.recoveryDataAsync(dataListener, p, where, true);
    }
}
