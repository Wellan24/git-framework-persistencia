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
@DataDBInfo(columns = {"ID", "NOMBRE", "SUELDO"}, tableName = "EMPLEADOS", primaryKey = {"ID", "NOMBRE"})
@DataTableInfo(fields = {"NOMBRE", "SUELDO"}, classes = {String.class, Integer.class}, columnNames = {"NOMBRE", "SUELDO"})
@DataDetailInfo(names = {"ID", "NOMBRE", "SUELDO"})
public class Empleado extends Data {

    public Empleado() {
        super(3);
    }

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
