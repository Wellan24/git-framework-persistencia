/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;


/**
 *
 * @author Oscar
 */
public interface DataProcessor{
    
    public void process(Data d);
    public void commit();
    public Object getProcesed();
    public boolean isValid(Data d);
}
