package com.example.payrollapi.service;

import com.example.payrollapi.domain.Payroll;
import com.example.payrollapi.feignClients.UserFeign;
import com.example.payrollapi.service.exceptions.ObjectNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PayRollService {

    private final Environment env;
    private final UserFeign feign;

    public Payroll getPayment(Long workerId, Payroll payroll){
        log.info("PAYROLL SERVICE ::: Get request on " + env.getProperty("local.server.port") + " port");

        try {
            var user = feign.findById(workerId).getBody();

            if(Objects.nonNull(user)){
                return new Payroll(
                        user.getName(),
                        payroll.getDescription(),
                        user.getHourlyPrice(),
                        payroll.getWorkedHours(),
                        user.getHourlyPrice() * payroll.getWorkedHours()
                );
            }

        } catch(FeignException.NotFound ex) {
            throw new ObjectNotFoundException("Object not found");
        } catch(Exception ex) {
            throw new IllegalArgumentException("Illegal argument exception");
        }
        return null;
    }

}
