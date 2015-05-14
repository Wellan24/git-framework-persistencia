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
public interface DataListener {

    public Class<? extends Data> getClassforHandle();

    public void handleDataRecoveryNotCached(ArrayList<Data> data, DataProcessor processor);

    public void handleDataRecoveryCached(String key, DataProcessor processor);
}
