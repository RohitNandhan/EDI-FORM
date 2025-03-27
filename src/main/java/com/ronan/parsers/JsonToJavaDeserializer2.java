package com.ronan.parsers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronan.dto.PlantDTO;

public class JsonToJavaDeserializer2 {

    // static String jsonFile="src/main/resources/plant-json-mapping.json";
    static String jsonFile="src/main/resources/plant-info-new.json";


    public static List<PlantDTO> deserialization(){
        List<PlantDTO> plantList=new ArrayList<>();


   try {
            ObjectMapper objectMapper = new ObjectMapper();
            
             plantList = objectMapper.readValue(new File(jsonFile),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, PlantDTO.class));
            
            for (PlantDTO plant : plantList) {
                // System.out.println(plant.getDesadv());
                // System.out.println(plant);

                // System.out.println();
                // System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plantList;
    }
}
