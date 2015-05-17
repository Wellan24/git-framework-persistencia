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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of this class is to give a bunch of general methods to Data
 * persistence
 *
 * @author Oscar
 */
public class DataManagementUtil {

    // TODO Transfomar esta clase en una que cambie en función de la 
    // base de datos y que sea una interfaz que se implemente
    /**
     * Executes a query without result.
     * @param connection
     * @param query
     * @return True if the query is success or false if not
     */
    public static boolean executeNonQuery(Connection connection, String query) {

        try (Statement statement = connection.createStatement()) {

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
    public synchronized static ResultSet executeQuery(Connection connection, String query) {

        try (Statement statement = connection.createStatement()) {

            return statement.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Insert a Data object to the DataBase. This uses the DataDBInfo interface,
     * or the name of the class and all its keys.
     *
     * @param connection
     * @param d
     * @return True if the insert is successfull or false otherwise.
     */
    public static boolean insertData(Connection connection, Data d) {

        StringBuilder text = createInsertQuery(d, devuelveOrdenDeColumnas(d.getClass()));
        DataManagementUtil.executeNonQuery(connection, text.toString());
        return existsByPrimaryKey(connection, d);
    }

    /**
     * This method check if the Data exists in the data base, comparing it with
     * the PrimaryKeys
     *
     * @param connection
     * @param d
     * @return True if it exists, false if not
     */
    public static boolean existsByPrimaryKey(Connection connection, Data d) {

        try (Statement statement = connection.createStatement()) {

            createSelectQueryByPrimaryKey("true", d);
            ResultSet rs = statement.executeQuery("");
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * This method check if the Data exists in the data base, comparing it 
     * using all the keys.
     *
     * @param connection
     * @param d
     * @return True if the Data exists, false if not
     */
    public static boolean existsByAllColumns(Connection connection, Data d) {
        
        Set<String> set = d.keySet();  
        return existsByColumns(connection, set.toArray(new String[set.size()]), d);
    }
    
    /**
     * This method check if the Data exists in the data base, comparing it 
     * using the given keys.
     *
     * @param connection
     * @param d
     * @param columns 
     * @return True if the Data exists, false if not
     */
    public static boolean existsByColumns(Connection connection, String[] columns, Data d) {

        try (Statement statement = connection.createStatement()) {

            Set<String> set = d.keySet();            
            ResultSet rs = statement.executeQuery(createSelectQueryByColumns("true", columns, d).toString());
            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Creates a Select Query with this format: "Select ${selectColumns} from ${table} where whereColumns[i] = ${columnValue} [, ...]
     * @param selectColumns
     * @param whereColumns
     * @param d
     * @return A String builder with the text of the query.
     */
    private static StringBuilder createSelectQueryByColumns(String selectColumns, String[] whereColumns, Data d) {

        StringBuilder text = new StringBuilder("Select ");
        text.append(selectColumns).append(" from ");
        text.append(devuelveNombreTablaDato(d.getClass())).append(" where ");

        for (int i = 0; i < whereColumns.length; i++) {

            String primaryKey = whereColumns[i];
            Object value = d.get(primaryKey);

            if (value instanceof String)
                text.append(primaryKey).append(" = '").append(value).append("'");
            else
                text.append(primaryKey).append(" = ").append(value);

            if (i != whereColumns.length - 1)
                text.append(",");
        }

        return text;
    }
    
    /**
     * Creates a Select Query with this format: "Select String.join(",", ${selectColumns}) from ${table} where whereColumns[i] = ${columnValue} [, ...]
     * @param selectColumns
     * @param whereColumns
     * @param d
     * @return A String builder with the text of the query.
     */
    private static StringBuilder createSelectQueryByColumns(String[] selectColumns, String[] whereColumns, Data d) {

        return createSelectQueryByColumns(String.join(",", selectColumns), whereColumns, d);
    }

    private static StringBuilder createSelectQueryByPrimaryKey(String columns, Data d) {

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

            if (i != primaryKeys.length - 1)
                text.append(",");
        }

        return text;
    }

    private static StringBuilder createSelectQueryByPrimaryKey(String[] columns, Data d) {

        return createSelectQueryByPrimaryKey(String.join(",", columns), d);
    }

    private static StringBuilder createInsertQuery(Data d, String[] claves) {

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

    public static boolean updateDato(Connection connection, Data d) {

        if (existsByPrimaryKey(connection, d)) {

            return executeNonQuery(connection, construyeSentenciaUpdate(d).toString());
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
