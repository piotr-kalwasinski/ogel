package com.example.ogel.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Runtime {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "machine_name")
    private String machineName;

    @Column(name = "variable_name")
    private String variableName;

    private Boolean isRunning;
}
