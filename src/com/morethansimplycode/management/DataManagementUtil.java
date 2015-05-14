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
    public static boolean ejecutarSentenciaUpdate(Connection conection, String textoSentencia) {

        Statement sentenciaLocal;
        ResultSet dev = null;
        try {

            sentenciaLocal = conection.createStatement();
            System.out.println(textoSentencia);
            int result = sentenciaLocal.executeUpdate(textoSentencia);

            return result == 1;
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
    public synchronized static ResultSet ejecutarSentenciaQuery(Connection conection, String textoSentencia) {

        Statement sentenciaLocal;
        ResultSet dev = null;
        try {

            sentenciaLocal = conection.createStatement();
            System.out.println(textoSentencia);
            dev = sentenciaLocal.executeQuery(textoSentencia);

        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dev;
    }

    public static boolean insertarDato(Connection conection, Data d) {

        String[] claves = devuelveOrdenDeColumnas(d.getClass());

        if (!comprobarExiste(conection, d)) {

            StringBuilder textoSentencia = construyeSentenciaInsert(d, claves);
            DataManagementUtil.ejecutarSentenciaUpdate(conection, textoSentencia.toString());
            return comprobarExiste(conection, d);
        }
        return false;
    }

    public static boolean comprobarExiste(Connection conection, Data d) {
        String primaryKey = devuelveClave(d.getClass());
        Statement sentenciaLocal;
        try {
            sentenciaLocal = conection.createStatement();
            if (!primaryKey.contains(" ")) {

                ResultSet rs = sentenciaLocal.executeQuery("Select " + primaryKey + " from " + devuelveNombreTablaDato(d.getClass())
                        + " where " + primaryKey + " = "
                        + ((d.get(primaryKey) instanceof String) ? "'" + d.get(primaryKey) + "'" : d.get(primaryKey)));

                return rs.next();

            } else {
                String[] key = primaryKey.split(" ");
                ResultSet rs = sentenciaLocal.executeQuery("Select " + key[0] + ", " + key[1] + " from " + devuelveNombreTablaDato(d.getClass())
                        + " where " + key[0] + " = "
                        + ((d.get(key[0]) instanceof String) ? "'" + d.get(key[0]) + "'" : d.get(key[0]))
                        + " AND " + key[1] + " = "
                        + ((d.get(key[1]) instanceof String) ? "'" + d.get(key[1]) + "'" : d.get(key[1]))
                );

                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

            return ejecutarSentenciaUpdate(conection, construyeSentenciaUpdate(d).toString());
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

    private static String devuelveClave(Class<? extends Data> d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
