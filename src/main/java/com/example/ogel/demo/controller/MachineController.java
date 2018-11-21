package com.example.ogel.demo.controller;

import com.example.ogel.demo.response.MachineProductionDTO;
import com.example.ogel.demo.service.MachineService;
import com.example.ogel.demo.util.Period;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//TODO Swagger documentation
//@Api(value = "Machines", description = "machines, their production of correct and damaged elements, working parameters ")
@RequestMapping("/rest/v1")

@Slf4j
public class MachineController {

    private MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping(value = "/machine/{machine-id}/production"
            , produces = {"application/json"})
    @Cacheable("production")
    public ResponseEntity<List<MachineProductionDTO>> getProduction(
            @PathVariable("machine-id") String machineId,
            //@ApiParam(value = WITH_BALANCES_DESC)
            @RequestParam(value = "period") Period period,
            @RequestParam(value = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam(value = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo) {
        log.debug(" I'm in getProduction");
        return new ResponseEntity<>(machineService.getProduction(machineId,dateFrom,dateTo, period), HttpStatus.OK);
    }


    //TODO:   the following endpoints should be created
    // /machine/{machine-id}/ - machine details  e.g. current status of running - GET
    // /machine/ - list of machines - GET
    // /machine/{machine-id}/defect  - list of aggregated defects - GET
    // /machine/{machine-id}/temperature - list of temp - max values for period
    //
    // Global exception handling and error services




}
