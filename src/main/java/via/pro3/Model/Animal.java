package via.pro3.Model;

public class Animal {
  private String animalId;
  private double weight; // Change weight to double for numerical value
  private String arrivalDate;
  private String status;

  public Animal(String animalId, double weight, String arrivalDate, String status) {
    this.animalId = animalId;
    this.weight = weight;
    this.arrivalDate = arrivalDate;
    this.status = status;
  }

  // Getters and setters
  public String getAnimalId() {
    return animalId;
  }

  public void setAnimalId(String animalId) {
    this.animalId = animalId;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}