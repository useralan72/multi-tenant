package com.example.service;

import com.example.domain.Meter;
import com.example.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AE2 on 29/06/2016.
 */
@Service
public class MeterServiceImpl implements MeterService{

    @Autowired
    MeterRepository meterRepository;

    @Override
    @Transactional (readOnly = true, propagation = Propagation.REQUIRED)
    public String getMeterId(Integer id) {
        Meter meter = meterRepository.findOne(id);
        return meter.getInputBy();
    }

    @Override
    @Transactional
    public Meter save(Meter meter) {
        return meterRepository.save(meter);
    }
}
