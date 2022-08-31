package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.grpc.RotarGrpc;
import com.example.grpc.RotarOuterClass;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();
        RotarGrpc.RotarBlockingStub stub = RotarGrpc.newBlockingStub(channel);
        RotarOuterClass.HelloRequest request = RotarOuterClass.HelloRequest
                .newBuilder().setName("neil").build();
        Iterator<RotarOuterClass.HelloResponse> response = stub.greeting(request);
        while (response.hasNext()) {
            System.out.println(response.next());
            channel.shutdown();
        }
       // System.out.println(response+ "the end");

    }
}
