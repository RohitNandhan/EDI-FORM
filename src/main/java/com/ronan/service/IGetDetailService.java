package com.ronan.service;

import java.util.List;

import com.ronan.entities.Plant;

public interface IGetDetailService {

    
    Plant getPlantDetails(int plantId, String countryType, String EDIType);

    

    List<Plant> getAllPlantDetails(int[] plantId, String countryType, String EDIType);
    
    

    
}
