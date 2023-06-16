package by.vstu.feign.dto;

import lombok.Data;

@Data
public class TokenResponse {

    private String access_token;

    private String scope;
}
