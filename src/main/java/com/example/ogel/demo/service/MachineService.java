package com.example.ogel.demo.service;

import com.example.ogel.demo.model.Production;
import com.example.ogel.demo.repository.ProductionRepository;
import com.example.ogel.demo.response.MachineProductionDTO;
import com.example.ogel.demo.util.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Service for machines
 * @author Piotr Kalwasinski
 * @since 2018-11-20
 */


@Service
public class MachineService {

    private static final  String PRODUCTION = "PRODUCTION";
    private ProductionRepository productionRepository;

    @Autowired
    public MachineService(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    //TODO Implement more service methods : getDefect - list of defects , getTemperature  - list of warning temperatures, getMachines - list of registering  machines
    /**
     * The method returns production list depending on the period and range of dates for given machine
     * @param machineName - machine name/ID
     * @param dateFrom - the start date and ome for retrieving production data
     * @param dateTo - the end of date and ome for retrieving production data
     * @param period - the period (look at *util.Period enum)
     * @return - the aggregation of machine production
     */
    public List<MachineProductionDTO> getProduction(String machineName, LocalDateTime dateFrom, LocalDateTime dateTo, Period period) {

        List<Production> productionList = productionRepository.findAllByMachineNameAndDatetimeFromIsAfterAndDatetimeToIsBeforeAndVariableName(
                machineName,
                dateFrom,
                dateTo,
                PRODUCTION
        );

        //TODO: check empty productionList


        if (Period.HOURLY.equals(period)) {
            return hourlyProduction(dateFrom, dateTo, productionList);
        } else {
            //TODO: implement more periods
            throw new NotImplementedException();
        }
    }


    /**
     *  The hourly production of machine.
     * @param dateFrom - start date for hourly calculate
     * @param dateTo - end date for hourly calculate
     * @param productionList - list for calculation
     * @return - list of hourly production aggregation
     */
    private List<MachineProductionDTO> hourlyProduction(
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            List<Production> productionList) {

        LocalDateTime iterationTimeByHour = dateFrom;
        List<MachineProductionDTO> result = new ArrayList<>();
        while (iterationTimeByHour.isBefore(dateTo)) {
            MachineProductionDTO machineProductionDTO = MachineProductionDTO.builder()
                    .machineName(productionList.get(0).getMachineName())
                    .startDateTime(iterationTimeByHour)
                    .build();
            machineProductionDTO.setProduction(BigDecimal.valueOf(getSumOfHourlyProduction(productionList, iterationTimeByHour)));
            machineProductionDTO.setStartHour(iterationTimeByHour.getHour());
            machineProductionDTO.setTimePeriod(Period.HOURLY);
            iterationTimeByHour = iterationTimeByHour.plusHours(1);
            machineProductionDTO.setEndDateTime(iterationTimeByHour);
            result.add(machineProductionDTO);
        }
        return result;
    }

    /**
     * SumSummary of hourly production
     * @param productionList - list for reduce and sum
     * @param productionTime - the hour (time) for sum
     * @return - sum of production
     */
    private int getSumOfHourlyProduction(List<Production> productionList, LocalDateTime productionTime) {
        return productionList.parallelStream()
                .filter(s -> s.getDatetimeFrom().getHour() == productionTime.getHour())
                .filter(s -> s.getDatetimeFrom().getDayOfYear() == productionTime.getDayOfYear())
                .reduce(0, (sum1, production) -> sum1 + production.getValue().intValue(), (a, b) -> a + b);
    }
}
