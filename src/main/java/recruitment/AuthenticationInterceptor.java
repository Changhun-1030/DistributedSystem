package recruitment;

import io.grpc.*;

public class AuthenticationInterceptor implements ServerInterceptor {
    private static final String VALID_API_KEY = "secure-key-2025"; 

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {

        Metadata.Key<String> apiKeyHeader = Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER);
        String apiKey = headers.get(apiKeyHeader);

        if (!VALID_API_KEY.equals(apiKey)) {
            call.close(Status.UNAUTHENTICATED.withDescription("Invalid or missing API Key"), headers);
            return new ServerCall.Listener<>() {}; 
        }

        return next.startCall(call, headers);
    }
}

