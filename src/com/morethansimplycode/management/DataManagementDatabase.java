/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Oscar
 */
public interface DataManagementDatabase {
    /**
     * Executes a query without result.
     *
     * @param connection
     * @param query
     * @return True if the query is success or false if not
     */
    public boolean executeNonQuery(Connection connection, String query);

    /**
     * @param textoSentencia
     * @return Devuelve un ResultSet con los datos de la consulta o null si hay
     * una excepción
     */
    public ResultSet executeQuery(Connection connection, String query);

    /**
     * Insert a Data object to the DataBase. This uses the DataDBInfo interface,
     * or the name of the class and all its keys.
     *
     * @param connection
     * @param d
     * @return True if the insert is successfull or false otherwise.
     */
    public boolean insertData(Connection connection, Data d);

    /**
     * This method check if the Data exists in the data base, comparing it with
     * the PrimaryKeys
     *
     * @param connection
     * @param d
     * @return True if it exists, false if not
     */
    public boolean existsByPrimaryKey(Connection connection, Data d);

    /**
     * This method check if the Data exists in the data base, comparing it using
     * all the keys.
     *
     * @param connection
     * @param d
     * @return True if the Data exists, false if not
     */
    public boolean existsByAllColumns(Connection connection, Data d);

    /**
     * This method check if the Data exists in the data base, comparing it using
     * the given keys.
     *
     * @param connection
     * @param d
     * @param columns
     * @return True if the Data exists, false if not
     */
    public boolean existsByColumns(Connection connection, String[] columns, Data d);

    /**
     * Creates a Select Query with this format: "Select ${selectColumns} from
     * ${table} where whereColumns[i] = ${columnValue} [, ...]
     *
     * @param selectColumns
     * @param whereColumns
     * @param d
     * @return A String builder with the text of the query.
     */
    public StringBuilder createSelectQueryByColumns(String selectColumns, String[] whereColumns, Data d);

    /**
     * Creates a Select Query with this format: "Select String.join(",",
     * ${selectColumns}) from ${table} where whereColumns[i] = ${columnValue} [,
     * ...]
     *
     * @param selectColumns
     * @param whereColumns
     * @param d
     * @return A String builder with the text of the query.
     */
    public StringBuilder createSelectQueryByColumns(String[] selectColumns, String[] whereColumns, Data d);

    /**
     * Creates a Select Query with this format: "Select ${columns} from ${table}
     * where primaryKey[i] = ${columnValue} [, ...]
     *
     * @param selectColumns
     * @param whereColumns
     * @param d
     * @return A String builder with the text of the query.
     */
    public StringBuilder createSelectQueryByPrimaryKey(String columns, Data d);

    /**
     * Creates a Select Query.
     * @param columns 
     * @param d
     * @return A String builder with the text of the query.
     */
    public StringBuilder createSelectQueryByPrimaryKey(String[] columns, Data d);

    /**
     * Creates a Insert Query.
     *
     * @param d
     * @param claves
     * @return An StringBuilder with the text of the Query
     */
    public StringBuilder createInsertQuery(Data d, String[] claves);

    /**
     * Creates a Insert Query with autonumeric key defined in DataDBInfo
     * annotation
     *
     * @param d
     * @param claves
     * @param autoNumKey The key for the autonum
     * @return An StringBuilder with the text of the Query
     */
    public StringBuilder createInsertQuery(Data d, String[] claves, boolean auto);

    public boolean updateDato(Connection connection, Data d);

    public StringBuilder construyeSentenciaUpdate(Data d);

    public String construyeSentenciaSelect(String[] claves, String nombreTabla);

    public String construyeSentenciaSelect(String[] claves, String nombreTabla, String where);
}
