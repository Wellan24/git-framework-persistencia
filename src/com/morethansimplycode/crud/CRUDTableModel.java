/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.crud;

import com.morethansimplycode.data.Data;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author Oscar
 */
public interface CRUDTableModel extends TableModel{
    
    /**
     * Use this to set the Data in the Model
     * Return itself for chain call.
     * @param d The data to show
     * @return itself
     */
    public CRUDTableModel setData(Data[] d);
    /**
     * Use this to set the Data in the Model
     * Return itself for chain call.
     * @param d The data to show
     * @return itself
     */
    public CRUDTableModel setData(List<Data> d);
    /**
     * Return the data which the model uses
     * @return an Array of Data
     */
    public Data[] getData();
}
