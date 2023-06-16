package by.vstu.feign.client;

import by.vstu.feign.config.AuthorizationClientInterceptor;
import by.vstu.feign.dto.CreateUserAccountDTO;
import by.vstu.feign.dto.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", configuration = AuthorizationClientInterceptor.class)
public interface AuthorizationServiceClient {

    @PostMapping("/token?grant_type=client_credentials")
    TokenResponse getOAuthClientToken();

    @PostMapping("/account/server-create")
    void createUserAccount(@RequestHeader("Authorization") String accessToken, @RequestBody CreateUserAccountDTO userAccountDTO);
}
