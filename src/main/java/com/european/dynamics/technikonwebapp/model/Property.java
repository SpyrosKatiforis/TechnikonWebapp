package com.european.dynamics.technikonwebapp.model;

import com.european.dynamics.technikonwebapp.model.enums.PropertyType;
import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.NoArgsConstructor;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "year_of_construction")
    private int yearOfConstruction;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type", nullable = false, length = 50)
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PropertyOwner owner;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private Set<PropertyRepair> repairs;
    
    @JsonbTransient
    public Set<PropertyRepair> getRepairs() {
        return repairs;
    }

    public Property(String address, int yearOfConstruction, PropertyType propertyType, PropertyOwner owner) {
    this.address = address;
    this.yearOfConstruction = yearOfConstruction;
    this.propertyType = propertyType;
    this.owner = owner;
}
    
    // toString method (optional)
}