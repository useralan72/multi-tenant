package com.example.controllers;

import com.example.domain.Meter;
import com.example.repository.MeterRepository;
import com.example.TenantContext;
import com.example.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;

@Controller
public class MeterController {
    @Autowired
    private MeterService meterService;

    @RequestMapping(path = "/meters", method= RequestMethod.POST)
    public ResponseEntity<?> createMeterReading(@RequestHeader("X-TenantID") String tenantName) {
       // TenantContext.setCurrentTenant(tenantName);

        Meter newMeter = new Meter();
        newMeter.setInputBy("fromController");
        Meter savedMeter = meterService.save(newMeter);

        return ResponseEntity.ok(savedMeter);
    }

    @RequestMapping(path = "/meters/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> getMeter(@PathVariable("id") Integer id) {
        String enteredBy = meterService.getMeterId(id);
        return ResponseEntity.ok(enteredBy);
    }
}
