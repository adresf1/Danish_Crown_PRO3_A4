package via.pro3.Model;

import java.util.ArrayList;
import java.util.List;
public class Tray {
  private int trayID;
  private String trayType;
  private int capacity;
  private double weight; // Changed from int to double for weight

  // List of parts (can be filled later by another method)
  private List<Part> parts;

  public Tray(int trayID, String trayType, int capacity, double weight) {
    this.trayID = trayID;
    this.trayType = trayType;
    this.capacity = capacity;
    this.weight = weight;
    this.parts = new ArrayList<>(); // Initialize empty parts list
  }

  // Getters and setters
  public int getTrayID() {
    return trayID;
  }

  public void setTrayID(int trayID) {
    this.trayID = trayID;
  }

  public String getTrayType() {
    return trayType;
  }

  public void setTrayType(String trayType) {
    this.trayType = trayType;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public List<Part> getParts() {
    return parts;
  }

  public void setParts(List<Part> parts) {
    this.parts = parts;
  }
}
