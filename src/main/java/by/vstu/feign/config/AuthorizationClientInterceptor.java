package by.vstu.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

public class AuthorizationClientInterceptor implements RequestInterceptor {
    private static final String CLIENT_BASIC_AUTH = "Basic QURNSVNTSU9OX1NFUlZJQ0U6TWR3RlZ4RFVTNDVF";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate.url().startsWith("/token")) {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, CLIENT_BASIC_AUTH);
        }
    }
}