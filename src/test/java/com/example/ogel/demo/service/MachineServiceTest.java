package com.example.ogel.demo.service;

import com.example.ogel.demo.model.Production;
import com.example.ogel.demo.repository.ProductionRepository;
import com.example.ogel.demo.response.MachineProductionDTO;
import com.example.ogel.demo.util.Period;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class MachineServiceTest {


    private static final String PRODUCTION = "PRODUCTION";
    @Mock
    private ProductionRepository productionRepository;

    @InjectMocks
    private MachineService service;

    private String machineName;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        machineName = "2x2 Machine";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateFrom = LocalDateTime.parse("2016-11-09 10:30", formatter);
        dateTo = LocalDateTime.parse("2016-11-09 11:30", formatter);
    }

    /**
     * Test aggregation by hour, machine and date range.
     */
    @Test
    void response_with_correct_production_for_machine() {

        given(productionRepository.findAllByMachineNameAndDatetimeFromIsAfterAndDatetimeToIsBeforeAndVariableName(
                machineName,
                dateFrom,
                dateTo,
                PRODUCTION)).willReturn(prepareTestProduction());


        List<MachineProductionDTO> result = service.getProduction(machineName,
                dateFrom,
                dateTo,
                Period.HOURLY);

        assertEquals(1, result.size());
        assertEquals(new BigDecimal(170), result.get(0).getProduction());
    }

    /**
     * Method for preparing tests data.
     *
     * @return - test data
     */
    private List<Production> prepareTestProduction() {
        return Lists.newArrayList(
                Production.builder()
                        .datetimeFrom(dateFrom)
                        .datetimeTo(dateTo)
                        .id(1L)
                        .machineName(machineName)
                        .value(new BigDecimal(100))
                        .variableName(PRODUCTION)
                        .build(),
                Production.builder()
                        .datetimeFrom(dateFrom)
                        .datetimeTo(dateTo)
                        .id(2L)
                        .machineName(machineName)
                        .value(new BigDecimal(50))
                        .variableName(PRODUCTION)
                        .build(),
                Production.builder()
                        .datetimeFrom(dateFrom)
                        .datetimeTo(dateTo)
                        .id(3L)
                        .machineName(machineName)
                        .value(new BigDecimal(20))
                        .variableName(PRODUCTION)
                        .build(),
                Production.builder()
                        .datetimeFrom(dateFrom.plusHours(1))
                        .datetimeTo(dateTo.plusHours(1))
                        .id(4L)
                        .machineName(machineName)
                        .value(new BigDecimal(40))
                        .variableName(PRODUCTION)
                        .build()

        );

    }

}