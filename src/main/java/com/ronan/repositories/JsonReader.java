package com.ronan.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ronan.dto.PlantDTO;
import com.ronan.dto.PlantFormDTO;
import com.ronan.entities.PARMA;
import com.ronan.parsers.JsonToJavaDeserializer2;

public class JsonReader {
    private static Map<Integer,PlantDTO> plantMap=new HashMap<>();
    private static List<PlantDTO> plantList=new ArrayList<>();


    public static List<PlantDTO> execute(PlantFormDTO plantFormDTO) {
        plantList=new ArrayList<>();
        List<PARMA> parmas=plantFormDTO.getSelectedPlants();
        // List<PlantDTO> plantDTOs=JsonToJavaDeserializer2.deserialization();

        mapping();
        
        for(PARMA parma:parmas){
            if(plantMap.containsKey(parma.getParma_id())){
                System.out.println(parma.getParma_id());
                System.out.println(plantMap.get(parma.getParma_id()));
                System.out.println("--------------------");

                plantList.add(plantMap.get(parma.getParma_id()));
            }   
        System.out.println("--------------------");
        System.out.println("--------------------MAPPING-----------------");
        
        // return plantList;
    }
            return plantList;
}

   
//    private String JsonPath="";

   public static void mapping() {
 
        // Read JSON from resources
        // String jsonData = readJsonFromResources("plant-json-mapping.json");
        // List<Plant> plantList=JsonParser.parseJson(jsonData);
        // System.out.println("JSON Content: \n" + plantList);

        List<PlantDTO> plantDTOs=JsonToJavaDeserializer2.deserialization();


        for(PlantDTO plant:plantDTOs){
            if(plantMap.containsKey(plant.getParma())){
                // System.err.println("===================");
            }else{
                // System.err.println("---------------------------------------------");
                // System.err.println("===================");
                // System.err.println("---------------------------------------------");
                // System.out.println(plant.toString());
                plantMap.put(plant.getParma(),plant);
            }
        }
   
}
}
