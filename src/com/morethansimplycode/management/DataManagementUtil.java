/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of this class is to give a bunch of general methods to Data
 * persistence
 *
 * @author Oscar
 */
public class DataManagementUtil {

    // TODO Refactorizar esta clase
    public static boolean executeNonQuery(Connection conection, String query) {

        try (Statement statement = conection.createStatement()) {

            return statement.executeUpdate(query) == 1;

        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * @param textoSentencia
     * @return Devuelve un ResultSet con los datos de la consulta o null si hay
     * una excepción
     */
    public synchronized static ResultSet executeQuery(Connection conection, String query) {

        try (Statement statement = conection.createStatement()) {

            return statement.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean insertarDato(Connection conection, Data d) {

        StringBuilder text = construyeSentenciaInsert(d, devuelveOrdenDeColumnas(d.getClass()));
        DataManagementUtil.executeNonQuery(conection, text.toString());
        return comprobarExiste(conection, d);
    }

    public static boolean comprobarExiste(Connection conection, Data d) {
        
        try (Statement statement = conection.createStatement()) {

            createSelectByPrimaryKey("true", d);
            ResultSet rs = statement.executeQuery("");
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static StringBuilder createSelectByPrimaryKey(String columns, Data d) {
        
        String[] primaryKeys = devuelveClave(d.getClass());
        
        StringBuilder text = new StringBuilder("Select ");
        text.append(columns).append(" from ");        
        text.append(devuelveNombreTablaDato(d.getClass())).append(" where ");
        
        for (int i = 0; i < primaryKeys.length; i++) {
            
            String primaryKey = primaryKeys[i];
            Object value = d.get(primaryKey);
            
            if (value instanceof String)
                text.append(primaryKey).append(" = '").append(value).append("'");
            else
                text.append(primaryKey).append(" = ").append(value);
            
            if(i != primaryKeys.length-1)
                text.append(",");
        }
        
        return text;
    }
    
    private static StringBuilder createSelectByPrimaryKey(String[] columns, Data d) {
        
        return createSelectByPrimaryKey(String.join(",", columns), d);
    }

    private static StringBuilder construyeSentenciaInsert(Data d, String[] claves) {

        StringBuilder textoSentencia = new StringBuilder("insert into ");
        textoSentencia.append(devuelveNombreTablaDato(d.getClass()));
        textoSentencia.append("(");
        for (String clave : claves) {

            textoSentencia.append(clave).append(",");
        }
        textoSentencia.replace(textoSentencia.length() - 1, textoSentencia.length(), ")");
        textoSentencia.append(" VALUES(");
        for (String clave : claves) {

            Object rec = d.get(clave);
            if (rec instanceof String || rec instanceof LocalDate) {

                textoSentencia.append("'");
                textoSentencia.append(rec.toString());
                textoSentencia.append("'");
            } else if (rec instanceof Integer) {

                textoSentencia.append(rec);
            } else if (rec instanceof Float) {

                textoSentencia.append(rec);
            } else {

                textoSentencia.append(rec);
            }
            textoSentencia.append(" ,");
        }
        textoSentencia.replace(textoSentencia.length() - 2, textoSentencia.length(), ");");

        return textoSentencia;
    }

    public static boolean updateDato(Connection conection, Data d) {

        if (comprobarExiste(conection, d)) {

            return executeNonQuery(conection, construyeSentenciaUpdate(d).toString());
        }

        return false;
    }

    public static StringBuilder construyeSentenciaUpdate(Data d) {

        String[] claves = devuelveOrdenDeColumnas(d.getClass());
        StringBuilder textoSentencia = new StringBuilder("update ");
        textoSentencia.append(devuelveNombreTablaDato(d.getClass()));
        textoSentencia.append(" set ");
        for (int i = 1; i < claves.length; i++) {

            Object rec = d.get(claves[i]);
            textoSentencia.append(claves[i]).append("=");

            if (rec instanceof String || rec instanceof LocalDate) {

                textoSentencia.append("'");
                textoSentencia.append(rec.toString());
                textoSentencia.append("'");
            } else if (rec instanceof Integer) {

                textoSentencia.append(rec);
            } else if (rec instanceof Float) {

                textoSentencia.append(rec);
            } else {

                textoSentencia.append(rec);
            }
            textoSentencia.append(" ,");
        }

        textoSentencia.replace(textoSentencia.length() - 2, textoSentencia.length(), " where ");
        textoSentencia.append(claves[0]).append(" = ").append(d.get(claves[0]));
        return textoSentencia;
    }

    public static String construyeSentenciaSelect(String[] claves, String nombreTabla) {

        StringBuilder dev = new StringBuilder("Select ");

        for (String clave : claves) {

            dev.append(clave).append(",");
        }

        dev.replace(dev.length() - 1, dev.length(), " from " + nombreTabla);

        return dev.toString();
    }

    public static String construyeSentenciaSelect(String[] claves, String nombreTabla, String where) {

        StringBuilder dev = new StringBuilder("Select ");

        for (String clave : claves) {

            dev.append(clave).append(",");
        }

        dev.replace(dev.length() - 1, dev.length(), " from ");
        dev.append(nombreTabla).append(" ").append(where);

        return dev.toString();
    }

    private static String[] devuelveOrdenDeColumnas(Class<? extends Data> d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String devuelveNombreTablaDato(Class<? extends Data> d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String[] devuelveClave(Class<? extends Data> d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
