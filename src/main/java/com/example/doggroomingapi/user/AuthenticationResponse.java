package com.example.doggroomingapi.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
This class exists strictly as a return value in registering a user and logging in.
When a user does either it is returned as a JWT for authentication and authorization.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
}
