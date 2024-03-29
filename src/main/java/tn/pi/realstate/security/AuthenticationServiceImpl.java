package tn.pi.realstate.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.pi.realstate.dto.request.SignUpRequest;
import tn.pi.realstate.dto.request.SigninRequest;
import tn.pi.realstate.dto.response.JwtAuthenticationResponse;
import tn.pi.realstate.entities.Role;
import tn.pi.realstate.entities.User;
import tn.pi.realstate.exceptions.UserAlreadyExistsException;
import tn.pi.realstate.repositories.UserRepository;

import static tn.pi.realstate.dto.Constants.USER_EXIST;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        // Check if a user with the given email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            // User with the given email already exists, handle accordingly (throw an exception, return an error response, etc.)
            // For simplicity, let's assume you want to throw an exception
            throw new UserAlreadyExistsException(USER_EXIST);
        }
        // User with the given email doesn't exist, proceed to create a new user
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        // Generate JWT token for the newly created user
        var jwt = jwtService.generateToken(user);

        // Return the JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Retrieve the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }

        // Get the email (username) of the authenticated user
        String email = authentication.getName();

        // Find the user by email in the repository
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Authenticated user not found"));

        // Generate JWT token for the authenticated user
        var jwt = jwtService.generateToken(user);

        // Return the JWT token along with user information in the response
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .user(user) // Assuming JwtAuthenticationResponse has a field to hold user information
                .build();
    }
}

