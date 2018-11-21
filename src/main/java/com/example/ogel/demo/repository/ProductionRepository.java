package com.example.ogel.demo.repository;

import com.example.ogel.demo.model.Production;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductionRepository extends CrudRepository<Production, Long> {


    List<Production> findAllByMachineNameAndDatetimeFromIsAfterAndDatetimeToIsBeforeAndVariableName(
            String machineName,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            String variable);


}
