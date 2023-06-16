package by.vstu.feign.dto;

import lombok.Data;

@Data
public class CreateUserAccountDTO {

    private String email;

    private String role = "ABITURIENT";

    public CreateUserAccountDTO(String email) {
        this.email = email;
    }
}
