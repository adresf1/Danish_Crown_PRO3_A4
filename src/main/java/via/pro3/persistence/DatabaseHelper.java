package via.pro3.persistence;

import org.postgresql.Driver;

import java.sql.*;


public class DatabaseHelper
{
  private static final String URL = "jdbc:postgresql://cornelius.db.elephantsql.com:5432/wmofsnqu";
  private static final String USER = "wmofsnqu";
  private static final String PASSWORD = "skGjU3q6Rj-7oFMLv3scHqqSPwCTvClr";


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
