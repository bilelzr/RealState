package tn.pi.realstate.security;

import tn.pi.realstate.dao.request.SignUpRequest;
import tn.pi.realstate.dao.request.SigninRequest;
import tn.pi.realstate.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
