package via.pro3.persistence;

import org.postgresql.Driver;

import java.sql.*;


public class DatabaseHelper
{
  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USER = "postgres";
  private static final String PASSWORD = "Kika12345";


  public Connection getConnection() {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }



}
