package tn.pi.realstate.security;

import tn.pi.realstate.dto.request.SignUpRequest;
import tn.pi.realstate.dto.request.SigninRequest;
import tn.pi.realstate.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
