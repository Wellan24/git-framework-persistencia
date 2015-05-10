/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.data;

/**
 *
 * @author Oscar
 */
public @interface DataTableInfo {
    
    String[] columns();
    Class[] clases();
}
