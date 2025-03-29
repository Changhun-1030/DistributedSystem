package recruitment;

import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.ArrayList;

public class InterviewSchedulerServiceImpl extends InterviewSchedulerServiceGrpc.InterviewSchedulerServiceImplBase {

    private final List<ScheduleResponse> schedules = new ArrayList<>();

    @Override
    public void scheduleInterview(ScheduleRequest request, StreamObserver<ScheduleResponse> responseObserver) {
        ScheduleResponse response = ScheduleResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Interview scheduled for " + request.getCandidateId())
                .setScheduledTime(request.getPreferredTime())
                .build();

        schedules.add(response);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getInterviewSchedule(ScheduleStatusRequest request, StreamObserver<ScheduleResponse> responseObserver) {
        for (ScheduleResponse schedule : schedules) {
            if (schedule.getMessage().contains(request.getCandidateId())) {
                responseObserver.onNext(schedule);
                responseObserver.onCompleted();
                return;
            }
        }

        ScheduleResponse response = ScheduleResponse.newBuilder()
                .setSuccess(false)
                .setMessage("No interview schedule found for candidate: " + request.getCandidateId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void modifyInterview(ModifyRequest request, StreamObserver<ScheduleResponse> responseObserver) {
        ScheduleResponse response = ScheduleResponse.newBuilder()
                .setSuccess(true)
                .setMessage(request.getCancel() ? "Interview canceled" : "Interview time updated")
                .setScheduledTime(request.getNewTime())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void streamAllInterviewSchedules(ScheduleStatusRequest request, StreamObserver<ScheduleResponse> responseObserver) {
        for (ScheduleResponse schedule : schedules) {
            responseObserver.onNext(schedule);
        }
        responseObserver.onCompleted();
    }
}
