package via.pro3.persistence;

import org.postgresql.Driver;
import via.pro3.Model.Animal;
import via.pro3.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper
{
  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USER = "postgres";
  private static final String PASSWORD = "Kika12345";

  public Connection getConnection()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
      return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public List<Animal> getAllAnimals()
  {
    List<Animal> animals = new ArrayList<>();
    String query = "SELECT * FROM \"Danish crown\".animal";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query))
    {

      while (resultSet.next())
      {
        Animal animal = new Animal(resultSet.getString("animalId"),
            resultSet.getString("weight"), resultSet.getString("arrivalDate"),
            resultSet.getString("status"));
        animals.add(animal);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return animals;
  }


  public List<Product> getAllProducts()
  {
    List<Product> products = new ArrayList<>();
    String query = "SELECT * FROM \"Danish crown\".product";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query))
    {

      while (resultSet.next())
      {
        Product product = new Product(resultSet.getString("productid"),
            resultSet.getString("packagetype"), resultSet.getString("animal"));
        products.add(product);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return products;
  }
}
