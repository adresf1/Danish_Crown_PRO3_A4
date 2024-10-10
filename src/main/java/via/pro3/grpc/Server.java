package via.pro3.grpc;

import io.grpc.ServerBuilder;
import via.pro3.service.Danish_Crown_Impl;

import java.io.IOException;

public class Server
{
  public static void main(String[] args)
      throws IOException, InterruptedException
  {
    Danish_Crown_Impl Danish_crown = new Danish_Crown_Impl();
    io.grpc.Server server = ServerBuilder.forPort(9090).addService(Danish_crown).build();
    server.start();
    System.out.println("Server running..");

    server.awaitTermination();
  }
}
