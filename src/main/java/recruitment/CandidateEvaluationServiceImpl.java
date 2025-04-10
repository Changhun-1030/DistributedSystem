package recruitment;

import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.Random;

public class CandidateEvaluationServiceImpl extends CandidateEvaluationServiceGrpc.CandidateEvaluationServiceImplBase {

    @Override
    public void evaluateCandidate(EvaluationRequest request, StreamObserver<EvaluationResponse> responseObserver) {
       
        double score = new Random().nextDouble() * 100;
        String recommendation = score > 70 ? "Highly recommended" : "Needs improvement";

        EvaluationResponse response = EvaluationResponse.newBuilder()
                .setSuccess(true)
                .setScore(score)
                .setRecommendation(recommendation)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getEvaluationResult(EvaluationStatusRequest request, StreamObserver<EvaluationResponse> responseObserver) {
      
        EvaluationResponse response = EvaluationResponse.newBuilder()
                .setSuccess(true)
                .setScore(80.0)
                .setRecommendation("Recommended for interview")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void recommendJobs(RecommendationRequest request, StreamObserver<RecommendationResponse> responseObserver) {
   
        List<Job> jobs = List.of(
                Job.newBuilder().setJobId("J01").setTitle("Software Engineer").setCompany("Google").setLocation("Seoul").build(),
                Job.newBuilder().setJobId("J02").setTitle("Data Scientist").setCompany("Naver").setLocation("Incheon").build(),
                Job.newBuilder().setJobId("J03").setTitle("Backend Developer").setCompany("Kakao").setLocation("Busan").build()
        );

        for (Job job : jobs) {
            RecommendationResponse response = RecommendationResponse.newBuilder()
                    .addJobs(job)
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<ChatMessage> realTimeChat(StreamObserver<ChatMessage> responseObserver) {
       
        return new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage message) {
                System.out.println("Received message from " + message.getSender() + ": " + message.getMessage());
                ChatMessage reply = ChatMessage.newBuilder()
                        .setSender("Server")
                        .setMessage("AI: " + message.getMessage())
                        .setTimestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
                responseObserver.onNext(reply);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
