package recruitment;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

public class InterviewSchedulerClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        
        Metadata headers = new Metadata();
        Metadata.Key<String> apiKeyHeader = Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER);
        headers.put(apiKeyHeader, "secure-key-2025");

        InterviewSchedulerServiceGrpc.InterviewSchedulerServiceBlockingStub stub =
            MetadataUtils.attachHeaders(
                InterviewSchedulerServiceGrpc.newBlockingStub(channel),
                headers
            );

        ScheduleRequest request = ScheduleRequest.newBuilder()
                .setCandidateId("A001")
                .setInterviewerId("I001")
                .setPreferredTime("2025-04-05 10:00")
                .build();

        ScheduleResponse response = stub.scheduleInterview(request);
        System.out.println(response.getMessage() + " at " + response.getScheduledTime());

        ModifyRequest modifyRequest = ModifyRequest.newBuilder()
                .setCandidateId("A001")
                .setNewTime("2025-04-06 11:00")
                .setCancel(false)
                .build();

        ScheduleResponse modifyResponse = stub.modifyInterview(modifyRequest);
        System.out.println(modifyResponse.getMessage());

        channel.shutdown();
    }
}
