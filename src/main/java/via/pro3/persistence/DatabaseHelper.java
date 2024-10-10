package via.pro3.persistence;

import org.postgresql.Driver;
import via.pro3.Model.Animal;

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
      String query = "SELECT * FROM Animal";

      try (Connection connection = getConnection();
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query)) {

        while (resultSet.next()) {
          Animal animal = new Animal(
              resultSet.getString("animalId"),
              resultSet.getString("weight"),
              resultSet.getString("arrivalDate"),
              resultSet.getString("status")
          );
          animals.add(animal);
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }

      return animals;
    }
  public void addAnimal(String animalId, String weight, String arrivalDate, String status) {
    String query = "INSERT INTO animals (animalId, weight, arrivalDate, status) VALUES (?, ?, ?, ?)";

    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setString(1, animalId);
      preparedStatement.setString(2, weight);
      preparedStatement.setString(3, arrivalDate);
      preparedStatement.setString(4, status);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
