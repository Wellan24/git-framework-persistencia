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
import com.morethansimplycode.formatting.Formattable;

/**
 *
 * @author Oscar
 */
@DataDBInfo(columns = {"ID", "NOMBRE", "SUELDO"},
        tableName = "EMPLEADOS", primaryKey = {"ID", "NOMBRE"})
@DataTableInfo(fields = {"ID", "NOMBRE", "SUELDO"},
        classes = {Integer.class, String.class, Integer.class},
        columnNames = {"Nº de Empleado", "Nombre", "Sueldo"})
@DataDetailInfo(fields = {"NOMBRE", "SUELDO"}, namesShow = {"Nombre", "Sueldo"})
public class Empleado extends Data implements Formattable{

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

    @Override
    public String toString() {
        
        return this.get("ID") + " - " + this.get("NOMBRE");
    }

    @Override
    public String toString(String format) {
        
        return "";
    }

}
