/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

/**
 *
 * @author Oscar
 */
public interface DataListener {
    
    public void handleDataRecovery(String key, DataProcessor processor);
}
