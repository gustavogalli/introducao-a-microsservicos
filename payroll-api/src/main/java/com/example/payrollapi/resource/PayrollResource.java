package com.example.payrollapi.resource;

import com.example.payrollapi.domain.Payroll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/payroll")
public class PayrollResource {

    @GetMapping(value = "/{workerId}")
    public ResponseEntity<Payroll> getPayment(@PathVariable Long workerId, @RequestBody Payroll payment){
        return ResponseEntity.ok().body(
                    new Payroll(
                            payment.getWorkerName(),
                            payment.getDescription(),
                            payment.getHourlyPrice(),
                            payment.getWorkedHours(),
                            payment.getHourlyPrice() * payment.getWorkedHours()
                ));
    }
}
