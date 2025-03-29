package recruitment;

import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

public class CandidateServiceImpl extends CandidateServiceGrpc.CandidateServiceImplBase {

    @Override
    public void submitApplication(ApplicationRequest request, StreamObserver<ApplicationResponse> responseObserver) {
        System.out.println("Application received from: " + request.getName());

        ApplicationResponse response = ApplicationResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Application submitted successfully for " + request.getName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCandidateStatus(StatusRequest request, StreamObserver<StatusResponse> responseObserver) {
        StatusResponse response = StatusResponse.newBuilder()
                .setStatus("Under Review")
                .setInterviewDate("2025-04-01 10:00 AM")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateApplication(UpdateRequest request, StreamObserver<ApplicationResponse> responseObserver) {
        ApplicationResponse response = ApplicationResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Application updated for candidate: " + request.getCandidateId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    //: Client Streaming 
    @Override
    public StreamObserver<ApplicationRequest> submitMultipleApplications(StreamObserver<ApplicationResponse> responseObserver) {
        return new StreamObserver<>() {
            private final List<ApplicationRequest> applications = new ArrayList<>();

            @Override
            public void onNext(ApplicationRequest applicationRequest) {
                System.out.println("Received multiple application from: " + applicationRequest.getName());
                applications.add(applicationRequest);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            } 

            @Override
            public void onCompleted() {
                ApplicationResponse response = ApplicationResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage(applications.size() + " applications received and processed successfully.")
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}
