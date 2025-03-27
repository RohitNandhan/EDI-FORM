package com.ronan.parsers;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronan.dto.PlantDTO;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            List<PlantDTO> plantList = objectMapper.readValue(new File("src/main/resources/plant-json-mapping.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, PlantDTO.class));
            
            for (PlantDTO plant : plantList) {
                System.out.println(plant.getDesadv());
                System.out.println(plant);

                System.out.println();
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
