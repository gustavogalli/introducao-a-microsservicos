package com.example.payrollapi.resource;

import com.example.payrollapi.domain.Payroll;
import com.example.payrollapi.domain.User;
import com.example.payrollapi.feignClients.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/payroll")
public class PayrollResource {

    @Autowired
    private UserFeign userFeign;

    @GetMapping(value = "/{workerId}")
    public ResponseEntity<Payroll> getPayment(@PathVariable Long workerId, @RequestBody Payroll payment){

        User user = userFeign.findById(workerId).getBody();

        return ResponseEntity.ok().body(
                    new Payroll(
                            user.getName(),
                            payment.getDescription(),
                            user.getHourlyPrice(),
                            payment.getWorkedHours(),
                            user.getHourlyPrice() * payment.getWorkedHours()
                ));
    }
}
