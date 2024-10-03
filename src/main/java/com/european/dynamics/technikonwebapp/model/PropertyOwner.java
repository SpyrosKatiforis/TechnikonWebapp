package com.european.dynamics.technikonwebapp.model;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.NoArgsConstructor;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "property_owner")
@Getter
@Setter
@NoArgsConstructor
public class PropertyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "vat_number", unique = true, nullable = false, length = 15)
    private String vatNumber;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Property> properties;
    
    @JsonbTransient
    public Set<Property> getProperties() {
    return properties;
    }

    public PropertyOwner(String vatNumber, String name, String surname, String address, String phoneNumber, String email, String username, String password) {
        this.vatNumber = vatNumber;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PropertyOwner{" +
                "ownerId=" + ownerId +
                ", vatNumber='" + vatNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}