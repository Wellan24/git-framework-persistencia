/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.easysql;

import com.morethansimplycode.formatting.StringFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscvic
 */
public class EasyConnection implements AutoCloseable {

    private Connection innerSqlConnection;

    public EasyConnection(String className, String url, String user, String password) {

        try {
            Class.forName(className);
            innerSqlConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EasyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ExecuteEasyQuery(Consumer<ResultSet> actionPerRecord, String query) throws SQLException {

        Statement statement = innerSqlConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next())
            actionPerRecord.accept(resultSet);

        dispose(statement, resultSet);
    }

    public void ExecuteEasyQuery(Consumer<ResultSet> actionPerRecord, String query, String... args) throws SQLException {

        query = StringFormatter.format(query, (Object[]) args);
        ExecuteEasyQuery(actionPerRecord, query);
    }

    public Boolean ExecuteEasyNonQuery(Consumer<ResultSet> actionPerRecord, String query) {

        try {

            Statement statement = innerSqlConnection.createStatement();
            int modified = statement.executeUpdate(query);

            innerSqlConnection.commit();
            dispose(statement, null);

            return modified > 0;

        } catch (SQLException ex) {

            rollback();
            Logger.getLogger(EasyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void ExecuteEasyNonQuery(Consumer<ResultSet> actionPerRecord, String query, String... args) throws SQLException {

        query = StringFormatter.format(query, (Object[]) args);
        ExecuteEasyQuery(actionPerRecord, query);
    }

    /**
     * Disposes the Statement and the ResultSet if not null
     *
     * @param statement The statement to dispose
     * @param resultSet The result set to dispose
     * @throws SQLException
     */
    private void dispose(Statement statement, ResultSet resultSet) throws SQLException {

        if (resultSet != null)
            resultSet.close();

        if (statement != null)
            statement.close();
    }

    private void rollback() {

        try {
            innerSqlConnection.rollback();
        } catch (SQLException ex1) {
            Logger.getLogger(EasyConnection.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    @Override
    public void close() throws Exception {

        innerSqlConnection.commit();
        innerSqlConnection.close();
    }

}
