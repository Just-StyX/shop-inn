package com.jsl.shop_inn.security.auth;

import com.jsl.shop_inn.common.base.Role;
import com.jsl.shop_inn.common.base.Token;
import com.jsl.shop_inn.email.EmailService;
import com.jsl.shop_inn.email.EmailTemplateName;
import com.jsl.shop_inn.exception.CustomerNotFoundException;
import com.jsl.shop_inn.models.Customer;
import com.jsl.shop_inn.repository.CustomerRepository;
import com.jsl.shop_inn.repository.RoleRepository;
import com.jsl.shop_inn.repository.TokenRepository;
import com.jsl.shop_inn.security.jwt.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService{
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    @Value("${application.security.code-length}")
    private int codeLength;

    @Override
    public void register(RegistrationRequest registrationRequest) throws MessagingException {
        Role customerRole = roleRepository.findByName("USER").orElseThrow(() -> new CustomerNotFoundException("User role not found"));
        Customer customer = Customer.builder()
                .firstname(registrationRequest.firstname())
                .lastname(registrationRequest.lastname())
                .email(registrationRequest.email())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(customerRole))
                .build();
        customerRepository.save(customer);
        sendValidationEmail(customer);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.password())
        );
        var claims = new HashMap<String, Object>();
        Customer customer = ((Customer) auth.getPrincipal());
        claims.put("fullName", customer.getFullName());

        var jwtToken = jwtService.generateToken(claims, customer);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public void activateAccount(String token) throws MessagingException {
        Token saveToken = tokenRepository.findByToken(token).orElseThrow(() -> new CustomerNotFoundException("Token not found"));
        if (LocalDateTime.now().isAfter(saveToken.getExpiredAt())) {
            sendValidationEmail(saveToken.getCustomer());
            throw new RuntimeException("Activation token expired. A new token was sent to your email.");
        }
        Customer customer = customerRepository.findById(saveToken.getCustomer().getId()).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        customer.setEnabled(true);
        customerRepository.save(customer);
        saveToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(saveToken);
    }

    @Override
    public String generateAndSendToken(Customer customer) {
        String generatedToken = generateActivationCode(codeLength);
        Token token = Token.builder()
                .token(generatedToken)
                .createdDate(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .customer(customer)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    @Override
    public void sendValidationEmail(Customer customer) throws MessagingException {
        String newToken = generateAndSendToken(customer);
        emailService.sendEmail(
                customer.getEmail(),
                customer.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken, "Account activation"
        );
    }

    @Override
    public String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
