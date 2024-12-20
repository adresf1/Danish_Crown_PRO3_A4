package via.pro3.persistence;

import via.pro3.Model.Animal;
import via.pro3.Model.Part;
import via.pro3.Model.Product;
import via.pro3.Model.Tray;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper {
  private static final String URL = "jdbc:sqlite:src/main/database/DC.db";

  public Connection getConnection() {
    try {
      // Load the SQLite JDBC driver
      Class.forName("org.sqlite.JDBC");
      return DriverManager.getConnection(URL);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<Animal> getAllAnimals() {
    List<Animal> animals = new ArrayList<>();
    String query = "SELECT * FROM Animal";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        Animal animal = new Animal(
            resultSet.getString("animalId"),
            resultSet.getDouble("weight"),
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

  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    String query = "SELECT * FROM Product";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        Product product = new Product(
            resultSet.getString("productId"),
            resultSet.getString("packageType"),
            resultSet.getString("animal")
        );
        products.add(product);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }
  public List<Tray> getAllTrays() {
    List<Tray> trays = new ArrayList<>();
    String query = "SELECT * FROM Tray";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        Tray tray = new Tray(
            resultSet.getInt("trayId"),
            resultSet.getString("trayType"),
            resultSet.getInt("capacity"),
            resultSet.getDouble("weight") );
        trays.add(tray);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return trays;
  }

  // Method to add a new tray
  public void addTray(Tray tray) {
    String query = "INSERT INTO Tray (trayId, trayType, capacity, weight) VALUES (?, ?, ?, ?)";

    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setInt(1, tray.getTrayID());
      preparedStatement.setString(2, tray.getTrayType());
      preparedStatement.setInt(3, tray.getCapacity());
      preparedStatement.setDouble(4, tray.getWeight());
      preparedStatement.setDouble(5, tray.getCapacity());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Method to retrieve all parts
  public List<Part> getAllParts() {
    List<Part> parts = new ArrayList<>();
    String query = "SELECT * FROM Part";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        Part part = new Part(
            resultSet.getInt("partId"),
            resultSet.getString("partType"),
            resultSet.getString("animalId"),
            resultSet.getString("weight"),
            resultSet.getInt("trayId")
        );
        parts.add(part);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return parts;
  }
  public void addPart(Part part) {
    String query = "INSERT INTO Part (partId, partType, weight, animalId, trayId) VALUES (?, ?, ?, ?, ?)";

    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1,part.getId());
      preparedStatement.setString(2, part.getPartType());
      preparedStatement.setString(3, part.getWieght());
      preparedStatement.setString(4, part.getAnimalid());
      preparedStatement.setInt(5, part.getTrayId());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Animal getAnimal(String animalId) {
    String query = "SELECT * FROM Animal WHERE animalId = ?";
    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setString(1, animalId);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return new Animal(
            resultSet.getString("animalId"),
            resultSet.getDouble("weight"),
            resultSet.getString("arrivalDate"),
            resultSet.getString("status")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null; // Return null if the animal is not found
  }

  public void addAnimal(Animal newAnimal)
  {
    String query = "INSERT INTO Animal (animalId, weight, arrivalDate, status) VALUES (?, ?, ?, ?)";

    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      // Set the parameters for the PreparedStatement
      preparedStatement.setString(1, newAnimal.getAnimalId());  // animalId
      preparedStatement.setDouble(2, newAnimal.getWeight());    // weight
      preparedStatement.setString(3, newAnimal.getArrivalDate()); // arrivalDate
      preparedStatement.setString(4, newAnimal.getStatus());     // status

      // Execute the update (insert the new animal into the database)
      preparedStatement.executeUpdate();
      System.out.println("Animal added successfully.");

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Error adding animal to the database.");
    }
  }
}