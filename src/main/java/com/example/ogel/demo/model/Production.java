package com.example.ogel.demo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "Production")
@Builder
public class Production {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "machine_name")
    private String machineName;

    @Column(name = "variable_name")
    private String variableName;

    @Column(name = "datetime_from")
    private LocalDateTime datetimeFrom;

    @Column(name = "datetime_to")
    private LocalDateTime datetimeTo;

    private BigDecimal value;

}
