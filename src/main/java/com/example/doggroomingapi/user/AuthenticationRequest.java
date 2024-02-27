package com.example.doggroomingapi.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
This class exists strictly as the data in a login request. This class is
used in UserService and UserController as a parameter for logging in.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String username;
    String password;
}
