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
        registry.registerService("CandidateService", new CandidateServiceImpl(), 50051);
        registry.registerService("InterviewSchedulerService", new InterviewSchedulerServiceImpl(), 50052);
        registry.registerService("CandidateEvaluationService", new CandidateEvaluationServiceImpl(), 50053);


        
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