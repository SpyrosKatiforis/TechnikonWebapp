package com.european.dynamics.technikonwebapp.model;

import com.european.dynamics.technikonwebapp.model.enums.RepairType;
import com.european.dynamics.technikonwebapp.model.enums.Status;
import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "property_repair")
@Getter
@Setter
@NoArgsConstructor
public class PropertyRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id")
    private Long repairId;

    @Enumerated(EnumType.STRING)
    @Column(name = "repair_type", nullable = false, length = 50)
    private RepairType repairType;

    @Column(name = "short_description", nullable = false, length = 255)
    private String shortDescription;

    @Column(name = "date_submitted", nullable = false)
    private LocalDateTime dateSubmitted;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "proposed_start_date")
    private LocalDateTime proposedStartDate;

    @Column(name = "proposed_end_date")
    private LocalDateTime proposedEndDate;

    @Column(name = "proposed_cost")
    private Double proposedCost;

    @Column(name = "owner_acceptance")
    private Boolean ownerAcceptance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private Status status;

    @Column(name = "actual_start_date")
    private LocalDateTime actualStartDate;

    @Column(name = "actual_end_date")
    private LocalDateTime actualEndDate;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @JsonbTransient
    public Property getProperty() {
        return property;
    }

    // Updated constructor without owner
    public PropertyRepair(RepairType repairType, String shortDescription, LocalDateTime dateSubmitted, Status status, Property property) {
        this.repairType = repairType;
        this.shortDescription = shortDescription;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.property = property;
    }

    @Override
    public String toString() {
        return "PropertyRepair{" +
                "repairId=" + repairId +
                ", repairType=" + repairType +
                ", shortDescription='" + shortDescription + '\'' +
                ", dateSubmitted=" + dateSubmitted +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}