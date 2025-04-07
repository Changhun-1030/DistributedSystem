package recruitment;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import recruitment.LoggerUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class CandidateServiceImpl extends CandidateServiceGrpc.CandidateServiceImplBase {

    @Override
    public void submitApplication(ApplicationRequest request, StreamObserver<ApplicationResponse> responseObserver) {
        try {
            if (request.getName() == null || request.getName().isBlank()) {
                LoggerUtil.log("Error: submitApplication - name is missing or blank");
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Candidate name must not be empty.")
                        .asRuntimeException());
                return;
            }

            LoggerUtil.log("submitApplication called for candidateId=" + request.getCandidateId() + ", name=" + request.getName());

            ApplicationResponse response = ApplicationResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Application submitted successfully for " + request.getName())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LoggerUtil.log("Unexpected error in submitApplication: " + e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal server error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getCandidateStatus(StatusRequest request, StreamObserver<StatusResponse> responseObserver) {
         LoggerUtil.log("getCandidateStatus called for candidateId=" + request.getCandidateId());

        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        String formattedDateTime = now.format(formatter);

        StatusResponse response = StatusResponse.newBuilder()
            .setStatus("Under Review")
            .setInterviewDate(formattedDateTime)  
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}

    @Override
    public void updateApplication(UpdateRequest request, StreamObserver<ApplicationResponse> responseObserver) {
        LoggerUtil.log("updateApplication called for candidateId=" + request.getCandidateId());

        ApplicationResponse response = ApplicationResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Application updated for candidate: " + request.getCandidateId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    // Client Streaming
    @Override
    public StreamObserver<ApplicationRequest> submitMultipleApplications(StreamObserver<ApplicationResponse> responseObserver) {
        return new StreamObserver<>() {
            private final List<ApplicationRequest> applications = new ArrayList<>();

            @Override
            public void onNext(ApplicationRequest applicationRequest) {
                LoggerUtil.log("submitMultipleApplications: received from name=" + applicationRequest.getName());
                applications.add(applicationRequest);
            }

            @Override
            public void onError(Throwable t) {
                LoggerUtil.log("submitMultipleApplications encountered error: " + t.getMessage());
                responseObserver.onError(Status.UNKNOWN.withDescription("Client stream failed").asRuntimeException());
            }

            @Override
            public void onCompleted() {
                LoggerUtil.log("submitMultipleApplications completed with total=" + applications.size());

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
