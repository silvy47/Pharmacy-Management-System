package app;

import java.sql.*;
import java.util.*;

public class Connect {

    public Connection conn;
    public Statement stmt;
    public ResultSet rs;

    public Connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:pharmacy.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//LOGIN 

    public ArrayList<String> selectWherelogin(String[] columnArray, String table, int[] whereCol, String[] values) {
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnArray);
        String sql = "SELECT " + columns + " FROM " + table + " WHERE ";
        for (int i = 0; i < whereCol.length; i++) {
            sql += columnArray[whereCol[i]] + " LIKE '" + values[i] + "' AND ";

        }
        sql = sql.substring(0, sql.length() - 4);
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String row = "";
                for (int i = 0; i < columnArray.length; i++) {
                    row = row + rs.getString(columnArray[i]) + "---";
                }

                row = row.substring(0, row.length() - 3);

                data.add(row);

            }
            System.out.println(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    //userPanel table
    ArrayList<String> select(String[] columnsArray, String table) {
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnsArray);
        String sql = "SELECT " + columns + " FROM " + table;
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i]) + "---";
                }
                row = row.substring(0, row.length() - 3);
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    //userPanel search
    ArrayList<String> selectWhere(String[] columnsArray, String table, int[] whereSearch, String[] turseniq) {

        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnsArray);
        String sql = "SELECT " + columns + " FROM " + table + " WHERE ";

        for (int i = 0; i < whereSearch.length; i++) {
            sql = sql + columnsArray[whereSearch[i]] + " like '%" + turseniq[i] + "%' or ";
        }

        sql = sql.substring(0, sql.length() - 4);
        System.out.println(sql);

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i]) + "---";

                }
                row = row.substring(0, row.length() - 3);
                data.add(row);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public ArrayList<String> selectOrderBy(String[] columnArray, String table, String column, String order) {
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnArray);
        String sql = "SELECT " + columns + " FROM " + table + " ORDER BY " + column + " " + order;
        //sql-> SELECT <columns> FROM <table> ORDER BY <column> ASC/DESC
        System.out.println(sql);

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String row = "";
                for (int i = 0; i < columnArray.length; i++) {
                    row = row + rs.getString(columnArray[i]) + "---";

                }

                row = row.substring(0, row.length() - 3);
                System.out.println(row);
                data.add(row);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public void update(String table, String[] columnArray, String[] valuesArray, String whereCol, String whereVal) {

        String sql = "UPDATE " + table + " SET ";
        for (int i = 0; i < columnArray.length; i++) {
            sql += columnArray[i] + " = '" + valuesArray[i] + "' ,";
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += " WHERE " + whereCol + " = '" + whereVal + "'";
        System.err.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //AddDrug INSERT
    public void insert(String table, String[] columnsArray, String[] valuesArray) {

        String columns = String.join(", ", columnsArray);
        String values = String.join("', '", valuesArray);
        String sql = "Insert into " + table + " (" + columns + ") values ('" + values + "')";

        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // UserPanel DELETE

    public void delete(String table, String column, String value) {
        String sql = "delete from " + table + " where " + column + " = " + value;
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
