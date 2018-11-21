package com.example.ogel.demo.response;

import com.example.ogel.demo.util.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MachineProductionDTO {

    private String machineName;
    private BigDecimal production;
    private Integer startHour;
    private Period timePeriod;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;



}