package com.jsl.shop_inn.security.auth;

import com.jsl.shop_inn.models.Customer;

public interface AuthenticationService {
    void register(RegistrationRequest registrationRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    void activateAccount(String token);
    String generateAndSendToken(Customer customer);
    void sendValidationEmail(Customer customer);
    String generateActivationCode(int length);
}
