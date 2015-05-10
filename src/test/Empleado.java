/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.morethansimplycode.data.Data;
import com.morethansimplycode.data.DataDBInfo;
import com.morethansimplycode.data.DataDetailInfo;
import com.morethansimplycode.data.DataTableInfo;

/**
 *
 * @author Oscar
 */
@DataDBInfo(fields = {"ID", "NOMBRE", "SUELDO"}, tableName = "EMPLEADOS", primaryKey = {"ID", "NOMBRE"})
@DataTableInfo(columns = {"NOMBRE", "SUELDO"}, clases = {String.class, Integer.class})
@DataDetailInfo(names = {"ID", "NOMBRE", "SUELDO"})
public class Empleado extends Data{

    public Empleado(int initialCapacity) {
        super(initialCapacity);
    }
    
    private Empleado(Empleado emple) {
        super(emple);
    }

    @Override
    public Data copia() {
        
        return new Empleado(this);
    }    
    
}
