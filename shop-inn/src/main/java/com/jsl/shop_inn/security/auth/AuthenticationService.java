package com.jsl.shop_inn.security.auth;

import com.jsl.shop_inn.models.Customer;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    void register(RegistrationRequest registrationRequest) throws MessagingException;
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    void activateAccount(String token) throws MessagingException;
    String generateAndSendToken(Customer customer);
    void sendValidationEmail(Customer customer) throws MessagingException;
    String generateActivationCode(int length);
}
