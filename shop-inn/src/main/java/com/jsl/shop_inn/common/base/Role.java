package com.jsl.shop_inn.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsl.shop_inn.models.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<Customer> customers;
}
