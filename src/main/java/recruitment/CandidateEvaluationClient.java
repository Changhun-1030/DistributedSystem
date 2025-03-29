package recruitment;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CandidateEvaluationClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        Metadata headers = new Metadata();
        Metadata.Key<String> apiKeyHeader = Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER);
        headers.put(apiKeyHeader, "secure-key-2025");

        
        CandidateEvaluationServiceGrpc.CandidateEvaluationServiceBlockingStub blockingStub =
            MetadataUtils.attachHeaders(
                CandidateEvaluationServiceGrpc.newBlockingStub(channel),
                headers
            );

        CandidateEvaluationServiceGrpc.CandidateEvaluationServiceStub asyncStub =
            MetadataUtils.attachHeaders(
                CandidateEvaluationServiceGrpc.newStub(channel),
                headers
            );

        EvaluationRequest evalRequest = EvaluationRequest.newBuilder()
                .setCandidateId("A001")
                .addSkills("Java")
                .addSkills("gRPC")
                .setJobDescription("Looking for skilled backend developer")
                .build();

        EvaluationResponse evalResponse = blockingStub.evaluateCandidate(evalRequest);
        System.out.println("Candidate Score: " + evalResponse.getScore());
        System.out.println("Recommendation: " + evalResponse.getRecommendation());

        // Bi-directional streaming: real-time chat
        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<ChatMessage> chatObserver = asyncStub.realTimeChat(new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage msg) {
                System.out.println("Server response: " + msg.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Chat ended.");
                latch.countDown();
            }
        });

        chatObserver.onNext(ChatMessage.newBuilder().setSender("Client").setMessage("Hello!").setTimestamp("2025-04-01").build());
        chatObserver.onNext(ChatMessage.newBuilder().setSender("Client").setMessage("I have a question regarding interview.").setTimestamp("2025-04-01").build());
        chatObserver.onCompleted();

        latch.await(5, TimeUnit.SECONDS);
        channel.shutdown();
    }
}

