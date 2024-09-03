package com.jsl.shop_inn.models;

import com.jsl.shop_inn.common.base.BaseEntity;
import com.jsl.shop_inn.common.base.Role;
import com.jsl.shop_inn.common.base.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer extends BaseEntity implements UserDetails, Principal {
    @NotNull
    @Column(nullable = false)
    private String firstname;
    @NotNull
    @Column(nullable = false)
    private String lastname;
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    private boolean accountLocked;
    private boolean enabled;

    @Embedded // not needed
    private Address billingAddress;

    @Embedded
    @AttributeOverride(name = "state", column = @Column(name = "shipping_state"))
    @AttributeOverride(name = "city", column = @Column(name = "shipping_city"))
    @AttributeOverride(name = "street", column = @Column(name = "shipping_street"))
    @AttributeOverride(name = "zipcode", column = @Column(name = "shipping_zipcode"))
    private Address shipping;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "customer")
    private List<Token> tokens;

    @Override
    public String getName() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }
}
