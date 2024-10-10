package via.pro3.service;

// Importer de genererede klasser fra gRPC
import com.google.protobuf.Message;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import via.pro3.Danish_Crown_PRO3.grpc.Danish_Crown_ImplGrpc;
import via.pro3.Danish_Crown_PRO3.grpc.*;
import via.pro3.Danish_Crown_PRO3.grpc.EmptyMessage;
import via.pro3.Model.Animal;
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

}

