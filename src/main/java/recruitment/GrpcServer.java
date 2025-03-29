package recruitment;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class GrpcServer {
    public static void main(String[] args) throws Exception {
        
        CandidateServiceImpl candidateService = new CandidateServiceImpl();
        InterviewSchedulerServiceImpl interviewSchedulerService = new InterviewSchedulerServiceImpl();
        CandidateEvaluationServiceImpl evaluationService = new CandidateEvaluationServiceImpl();

      
        ServiceRegistry registry = new ServiceRegistry();
        registry.registerService("CandidateService", candidateService);
        registry.registerService("InterviewSchedulerService", interviewSchedulerService);
        registry.registerService("CandidateEvaluationService", evaluationService);

        
        Server server = ServerBuilder.forPort(50051)
            .addService(ServerInterceptors.intercept(candidateService, new AuthenticationInterceptor()))
            .addService(ServerInterceptors.intercept(interviewSchedulerService, new AuthenticationInterceptor()))
            .addService(ServerInterceptors.intercept(evaluationService, new AuthenticationInterceptor()))
            .build();

        server.start();
        System.out.println("gRPC Server started on port 50051");
        server.awaitTermination();
    }
}