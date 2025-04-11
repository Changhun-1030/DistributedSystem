package recruitment;

import io.grpc.stub.StreamObserver;
import java.util.List;

public class CandidateEvaluationServiceImpl extends CandidateEvaluationServiceGrpc.CandidateEvaluationServiceImplBase {

    @Override
public void evaluateCandidate(EvaluationRequest request, StreamObserver<EvaluationResponse> responseObserver) {
    List<String> skills = request.getSkillsList();
    double score = 50;

    if (!skills.isEmpty()) {
        int maxScore = 0;

        for (String skill : skills) {
            int skillScore;
            switch (skill.toLowerCase()) {
                case "java":
                case "c++":
                    skillScore = 90;
                    break;
                case "python":
                    skillScore = 80;
                    break;
                case "mysql":
                    skillScore = 70;
                    break;
                case "html":
                    skillScore = 60;
                    break;
                default:
                    skillScore = 55;
            }

            if (skillScore > maxScore) {
                maxScore = skillScore;
            }
        }

        score = maxScore;
    }

    String recommendation = switch ((int) score) {
        case 90 -> "Excellent in core programming";
        case 80 -> "Very good in scripting";
        case 70 -> "Good database skills";
        case 60 -> "Basic web knowledge";
        case 55 -> "Some general skills";
        default -> "No significant skill detected";
    };

    EvaluationResponse response = EvaluationResponse.newBuilder()
            .setSuccess(true)
            .setScore(score)
            .setRecommendation(recommendation)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}

    @Override
public void recommendJobs(RecommendationRequest request, StreamObserver<RecommendationResponse> responseObserver) {

    List<Job> jobs = List.of(
            Job.newBuilder().setJobId("J01").setTitle("Engineer").setCompany("Samsung").setLocation("KOREA").build(),
            Job.newBuilder().setJobId("J02").setTitle("Developer").setCompany("Apple").setLocation("US").build(),
            Job.newBuilder().setJobId("J03").setTitle("Cybersecurity").setCompany("Google").setLocation("IRELAND").build(),
            Job.newBuilder().setJobId("J04").setTitle("Web Designer").setCompany("TSMC").setLocation("TAIWAN").build(),
            Job.newBuilder().setJobId("J05").setTitle("Data Analyst").setCompany("Amazon").setLocation("UK").build()
    );

    RecommendationResponse response = RecommendationResponse.newBuilder()
            .addAllJobs(jobs) 
            .build();

    responseObserver.onNext(response);  
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
            .setMessage(message.getMessage())  
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
