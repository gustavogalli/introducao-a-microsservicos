package com.example.payrollapi.resource;

import com.example.payrollapi.domain.Payroll;
import com.example.payrollapi.domain.User;
import com.example.payrollapi.feignClients.UserFeign;
import com.example.payrollapi.service.PayRollService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/payroll")
public class PayrollResource {

    @Autowired
    private UserFeign userFeign;

    private final PayRollService service;

    @GetMapping(value = "/{workerId}")
    public ResponseEntity<Payroll> getPayment(@PathVariable Long workerId, @RequestBody Payroll payment){

        User user = userFeign.findById(workerId).getBody();

        return ResponseEntity.ok().body(service.getPayment(workerId, payment));
    }
}
