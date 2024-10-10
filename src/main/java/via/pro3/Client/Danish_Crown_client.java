package via.pro3.Client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import via.pro3.Danish_Crown_PRO3.grpc.Danish_Crown_ImplGrpc;
import via.pro3.Danish_Crown_PRO3.grpc.EmptyMessage;
import via.pro3.Danish_Crown_PRO3.grpc.animalListResponse;
import via.pro3.Danish_Crown_PRO3.grpc.animalrequest;

public class Danish_Crown_client {

  public static void main(String[] args) {
    // Opret en kanal til serveren på localhost port 9090
    ManagedChannel managedChannel = ManagedChannelBuilder
        .forAddress("localhost", 9090)
        .usePlaintext()
        .build();

    // Opret en stub til at kalde serverens tjenester
    Danish_Crown_ImplGrpc.Danish_Crown_ImplBlockingStub danishCrownImplBlockingStub =
        Danish_Crown_ImplGrpc.newBlockingStub(managedChannel);

    // Send en tom anmodning og modtag listen af dyr som svar
    EmptyMessage request = EmptyMessage.getDefaultInstance();
    animalListResponse responseAnimal = danishCrownImplBlockingStub.getAllAnimals(request);

    // Iterer over listen af dyr
    for (animalrequest animal : responseAnimal.getAnimalsList()) {
      System.out.println("Animal ID: " + animal.getAnimalId());
      System.out.println("Weight: " + animal.getWeight());
      System.out.println("Arrival Date: " + animal.getArrivalDate());
      System.out.println("Status: " + animal.getStatus());
      System.out.println("-----");
    }

    // Luk kanalen
    managedChannel.shutdown();
  }
}
