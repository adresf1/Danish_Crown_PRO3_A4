package via.pro3.service;

// Importer de genererede klasser fra gRPC
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import via.pro3.Danish_Crown_PRO3.grpc.Danish_Crown_ImplGrpc;
import via.pro3.Danish_Crown_PRO3.grpc.*;
import via.pro3.Danish_Crown_PRO3.grpc.EmptyMessage;
import via.pro3.Model.Animal;
import via.pro3.Model.Part;
import via.pro3.Model.Product;
import via.pro3.Model.Tray;
import via.pro3.persistence.DatabaseHelper;

import java.util.List;

public class Danish_Crown_Impl
    extends Danish_Crown_ImplGrpc.Danish_Crown_ImplImplBase
{
  public void getAllAnimals(EmptyMessage request,
      StreamObserver<animalListResponse> responseObserver)
  {
    DatabaseHelper db = new DatabaseHelper();
    try
    {
      List<Animal> animalsDB = db.getAllAnimals();

      // Byg response med listen af dyr
      animalListResponse.Builder responseBuilder = animalListResponse.newBuilder();

      for (Animal animal : animalsDB)
      {
        responseBuilder.addAnimals(
            animalrequest.newBuilder().setAnimalId(animal.getAnimalId())
                .setWeight(animal.getWeight())
                .setArrivalDate(animal.getArrivalDate())
                .setStatus(animal.getStatus()).build());
      }

      // Send response tilbage til klienten
      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();

    }
    catch (Exception e)
    {
      responseObserver.onError(e);
    }

  }
  public void getAllProducts(EmptyMessage request,
      StreamObserver<productListResponse> responseObserver)
  {
    DatabaseHelper db = new DatabaseHelper();
    try
    {
      List<Product> productsDB = db.getAllProducts();

      // Byg response med listen af products
      productListResponse.Builder responseBuilder = productListResponse.newBuilder();

      for (Product product : productsDB)
      {
        responseBuilder.addProducts(
            productrequest.newBuilder().setProductid(product.getProductid())
                .setPackagetype(product.getPackagetype())
                .setAnimal(product.getAnimal()));
      }

      // Send response tilbage til klienten
      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();

    }
    catch (Exception e)
    {
      responseObserver.onError(e);
    }

  }

  @Override public void getAllTrays(EmptyMessage request, StreamObserver<trayListResponse> responseObserver)
  {
    DatabaseHelper db = new DatabaseHelper();

    try{
      List<Tray> traysDB = db.getAllTrays();

      trayListResponse.Builder responseBuilder = trayListResponse.newBuilder();

      for (Tray tray : traysDB)
      {
        responseBuilder.addTrays(
            trayRequest.newBuilder().setTrayID(tray.getTrayID()).setTrayType(tray.getTrayType()).setCapacity(tray.getCapacity()).setWeight(tray.getWeight()));
      }

      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();
    } catch (Exception e)
    {
      responseObserver.onError(e);
    }
  }

  @Override public void getAllParts(EmptyMessage request,
      StreamObserver<partListResponse> responseObserver)
  {
    DatabaseHelper db = new DatabaseHelper();

    try {
      List<Part> partsDB = db.getAllParts();

      partListResponse.Builder responseBuilder = partListResponse.newBuilder();

      for (Part part : partsDB)
      {
        responseBuilder.addParts(partRequest.newBuilder().setId(part.getId()).setPartType(part.getPartType()).setAnimal(part.getAnimalid()).setWeight(part.getWieght()).setId(part.getId()));
      }
      responseObserver.onNext(responseBuilder.build());
      responseObserver.onCompleted();
    } catch (Exception e)
    {
      responseObserver.onError(e);
    }
  }

  @Override public void getAnimal(animalrequest request,
      StreamObserver<animalresponse> responseObserver)
  {
    DatabaseHelper db = new DatabaseHelper();

    try{
      Animal animal = db.getAnimal(request.getAnimalId());

      if (animal != null) {
        // Build the response with the retrieved animal
        animalresponse response = animalresponse.newBuilder()
            .setAnimalId(animal.getAnimalId())
            .setWeight(animal.getWeight())
            .setArrivalDate(animal.getArrivalDate())
            .setStatus(animal.getStatus())
            .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
      } else {
        // If no animal is found, send an error
        responseObserver.onError(new Exception("Animal not found with ID: " + request.getAnimalId()));
      }

    } catch (Exception e)
    {
      responseObserver.onError(e);
    }
  }

  @Override public void addAnimal(animalrequest request,
      StreamObserver<animalresponse> responseObserver)
  {
    try {
      // Extract information from the request
      String animalId = request.getAnimalId();
      double weight = request.getWeight();
      String arrivalDate = request.getArrivalDate();
      String status = request.getStatus();

      // Add logic to store the animal in your database or perform the relevant action
      Animal newAnimal = new Animal(animalId, weight, arrivalDate, status);

      // Example: Save the animal to the database
      DatabaseHelper dbHelper = new DatabaseHelper();
      dbHelper.addAnimal(newAnimal);  // Assuming you have a method in your DatabaseHelper

      // Build the response
      animalresponse response = animalresponse.newBuilder()
          .setMessage("Animal added successfully")
          .setAnimalId(animalId)
          .build();

      // Send the response back to the client
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (Exception e) {
      // Handle error and send failure response
      responseObserver.onError(
          Status.INTERNAL.withDescription("Failed to add animal").asRuntimeException());
    }
  }

  @Override public void addPart(partRequest request,
      StreamObserver<partResponse> responseObserver)
  {
    try {
      // Extract information from the request
      int id = request.getId();
      String partType = request.getPartType();
      String weight = request.getWeight();
      String animalId = request.getAnimal();
      int trayId = request.getTray();

      // Create a new Part object using the extracted information
      Part newPart = new Part(id, partType, animalId, weight, trayId); // ID is auto-generated

      // Add logic to store the part in the database
      DatabaseHelper dbHelper = new DatabaseHelper();
      dbHelper.addPart(newPart);  // Assuming you have an addPart method in your DatabaseHelper

      // Build the response
      partResponse response = partResponse.newBuilder()
          .setMessage("Part added successfully")
          .setPartType(partType)
          .setWeight(weight)
          .setAnimal(animalId)
          .setTray(trayId).setId(id)
          .build();

      // Send the response back to the client
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (Exception e) {
      // Handle error and send failure response
      responseObserver.onError(
          Status.INTERNAL.withDescription("Failed to add part").asRuntimeException());
    }
  }

  @Override public void addTray(trayRequest request,
      StreamObserver<trayResponse> responseObserver)
  {
    try {
      // Extract information from the request
      int trayId = request.getTrayID();
      String trayType = request.getTrayType();
      int capacity = request.getCapacity();
      double weight = request.getWeight();

      // Create a new Tray object using the extracted information
      Tray newTray = new Tray(trayId, trayType, capacity, weight);

      // Add logic to store the tray in the database
      DatabaseHelper dbHelper = new DatabaseHelper();
      dbHelper.addTray(newTray);  // Assuming you have an addTray method in your DatabaseHelper

      // Build the response
      trayResponse response = trayResponse.newBuilder()
          .setMessage("Tray added successfully")
          .setTrayID(trayId)
          .setTrayType(trayType)
          .setCapacity(capacity)
          .setWeight(weight)
          .build();

      // Send the response back to the client
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (Exception e) {
      // Handle error and send failure response
      responseObserver.onError(
          Status.INTERNAL.withDescription("Failed to add tray").asRuntimeException());
    }
  }
}

