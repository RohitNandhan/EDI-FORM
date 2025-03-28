package com.ronan.parsers;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronan.dto.PlantDTO;

public class JsonToJavaDeserializer2 {

    // static String jsonFile="src/main/resources/plant-json-mapping.json";
    static String jsonFile="plant-info-new.json";


    public static List<PlantDTO> deserialization(){
        List<PlantDTO> plantList=new ArrayList<>();


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            // Load JSON from resources using getResourceAsStream()
            InputStream inputStream = JsonToJavaDeserializer2.class.getClassLoader().getResourceAsStream(jsonFile);
            if (inputStream == null) {
                throw new RuntimeException("Resource file '" + jsonFile + "' not found!");
            }
            
            // Deserialize JSON into List<PlantDTO>
            plantList = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, PlantDTO.class));
            
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
