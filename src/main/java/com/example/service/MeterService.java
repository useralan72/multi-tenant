package com.example.service;

import com.example.domain.Meter;

/**
 * Created by AE2 on 29/06/2016.
 */
public interface MeterService {

    String getMeterId(Integer id);

    Meter save(Meter meter);
}
