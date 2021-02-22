package nl.novi.eindopdracht.payload.request;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

}
