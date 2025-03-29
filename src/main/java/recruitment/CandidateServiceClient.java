package recruitment;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

public class CandidateServiceClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        
       
        Metadata headers = new Metadata();
        Metadata.Key<String> apiKeyHeader = Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER);
        headers.put(apiKeyHeader, "secure-key-2025");

        
        CandidateServiceGrpc.CandidateServiceBlockingStub stub =
            MetadataUtils.attachHeaders(
                CandidateServiceGrpc.newBlockingStub(channel),
                headers
            );

        ApplicationRequest request = ApplicationRequest.newBuilder()
                .setCandidateId("A001")
                .setName("CHANGHHUNYOO")
                .setResumeUrl("http://resume.url")
                .addSkills("Java")
                .addSkills("gRPC")
                .setJobId("J001")
                .build();
  
        ApplicationResponse response = stub.submitApplication(request);
        System.out.println(response.getMessage());

        StatusResponse statusResponse = stub.getCandidateStatus(
                StatusRequest.newBuilder().setCandidateId("A001").build()
        );
        System.out.println("Status: " + statusResponse.getStatus());
        System.out.println("Interview Date: " + statusResponse.getInterviewDate());

        channel.shutdown();
    }
}


