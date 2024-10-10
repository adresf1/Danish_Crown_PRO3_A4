package via.pro3;

import via.pro3.persistence.DatabaseHelper;

import java.sql.Connection;

public class Main
{
  public static void main(String[] args)
  {

    DatabaseHelper dbHelper = new DatabaseHelper();
    Connection connection = dbHelper.getConnection();
    if (connection != null) {
      System.out.println("Connection successful!");
    } else {
      System.out.println("Failed to connect.");
    }
  }
}