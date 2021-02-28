package com.hrms.utils;

import java.sql.*;
import java.util.*;

public class DBUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * this method will establish/create a connection with db
     */
    public static Connection getConnection() {//throws SQLException {//checked exception from Java perspective

        try {
//            creating connection
            connection = DriverManager.getConnection(
                    ConfigsReader.getPropertyValue("dbUrl"),
                    ConfigsReader.getPropertyValue("dbUsername"),
                    ConfigsReader.getPropertyValue("dbPassword"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * This method will return ResultSet object
     *
     * @param query
     * @return
     */
    public static ResultSet getResultSet(String query) {

        try {
//            creating a Statement
            statement = getConnection().createStatement();
//            executing query and store results from DB in ResultSet object
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

//    get value from resultSet
    /**
     * This method will execute the given sql query, return the data from DB as a List of Maps and close connection
     * From here we can retrieve any data regardless of number of rows, regardless of number of columns
     * everything will be store dynamically
     *
     * @param query
     * @return
     */
    public static List<Map<String, String>> getDBDataIntoListOfMaps(String query) {

        List<Map<String, String>> dbListOfMaps = new ArrayList<>();
        Map<String, String> map;
        try {
            ResultSetMetaData resultSetMetaData = getResultSet(query).getMetaData();//info about ResultSet table
            // resultSet.getMetaData() gives name of the columns, number of columns, etc
            while (resultSet.next()) {//loops through all rows
                map = new LinkedHashMap<>();
                for (int col = 1; col <= resultSetMetaData.getColumnCount(); col++) {
                    map.put(resultSetMetaData.getColumnName(col), resultSet.getString(col));//loops through each columns of the row
                }
                dbListOfMaps.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dbListOfMaps;
    }

    /**
     * This method will execute the given sql query, return the data from DB as a List of String and close connection
     *
     * @param query
     * @return
     */
    public static List<String> getDBDataIntoList(String query) {

        List<String> dbList = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = getResultSet(query).getMetaData();//info about ResultSet table
            while (resultSet.next()) {//loops through all rows
                for (int row = 1; row <= resultSetMetaData.getColumnCount(); row++) {
                    String data = resultSet.getString(row);
                    dbList.add(data);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dbList;
    }

//    /**
//     * This method will execute the given sql query, return the data from DB as a Map of String and close connection
//     * @param query
//     * @return
//     */
//    public static Map<String, String> getDBDataIntoMap(String query) {
//        Map<String, String> dbMap = new LinkedHashMap<>();
//        try {
//            ResultSetMetaData resultSetMetaData = getResultSet(query).getMetaData();//info about ResultSet table
//            while (resultSet.next()) {//step into one row
//                for (int col = 1; col <= resultSetMetaData.getColumnCount(); col++) {
//                    dbMap.put(resultSetMetaData.getColumnName(col), resultSet.getString(col));//loops through each columns of the row
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//        return dbMap;
//    }


    public static void closeConnection() {//where to use?

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

