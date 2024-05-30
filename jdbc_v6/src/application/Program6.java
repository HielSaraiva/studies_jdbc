package src.application;

import src.db.DB6;
import src.db.Db6Exception;

import java.sql.*;

public class Program6 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        try {
            conn = DB6.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            /*
            int x = 1;
            if (x < 2) {
                throw new SQLException("Fake error");
            }
            */

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("rows1 = " + rows1);
            System.out.println("rows2 = " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new Db6Exception("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new Db6Exception("Error while rolling back! Caused by: " + ex.getMessage());
            }
        } finally {
            DB6.closeStatement(st);
            DB6.closeConnection();
        }
    }
}