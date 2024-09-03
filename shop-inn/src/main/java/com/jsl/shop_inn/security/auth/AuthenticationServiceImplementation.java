package com.jsl.shop_inn.security.auth;

import com.jsl.shop_inn.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService{
    @Override
    public void register(RegistrationRequest registrationRequest) {

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return null;
    }

    @Override
    public void activateAccount(String token) {

    }

    @Override
    public String generateAndSendToken(Customer customer) {
        return "";
    }

    @Override
    public void sendValidationEmail(Customer customer) {

    }

    @Override
    public String generateActivationCode(int length) {
        return "";
    }
}
