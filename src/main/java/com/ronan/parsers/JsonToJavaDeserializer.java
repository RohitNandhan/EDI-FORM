// package com.ronan.parsers;

// import java.io.File;
// import java.io.IOException;
// import java.io.InputStream;
// import java.nio.charset.StandardCharsets;
// import java.util.List;
// import java.util.Scanner;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.ronan.dto.DispatchAdviceDTO;
// import com.ronan.dto.PlantDTO;
// import com.ronan.repositories.JsonReader;

// public class JsonToJavaDeserializer {
//     public static void main(String[] args) {
//         ObjectMapper objectMapper = new ObjectMapper();
//         try {
         
//         // Read JSON from resources
//         String jsonData = readJsonFromResources("plant-json-mapping.json");
//         // List<Plant> plantList=JsonParser.parseJson(jsonData);
//         // List<Plant> plantList=objectMapper.readValue(jsonData, objectMapper.getTypeFactory().constructCollectionType(List.class, PlantDTO.class));
//             List<PlantDTO> plantList = objectMapper.readValue(new File("plant-json-mapping.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, PlantDTO.class));
            
//             // Print parsed data
//             for (PlantDTO plantDTO : plantList) {
//                 System.out.println("Parma: " + plantDTO.getParma());
//                 if (plantDTO.getDesAdvDto() != null) {
//                     for (DispatchAdviceDTO desadv : plantDTO.getDesAdvDto()) {
//                         System.out.println("  Generic Message: " + desadv.getGenericMessage());
//                         System.out.println("  Comments: " + desadv.getComments());
//                     }
//                 }
//                 if (plantDTO.getInvoice() != null) {
//                     System.out.println("  Invoice Data: " + plantDTO.getInvoice());
//                 }
//                 if (plantDTO.getContact() != null) {
//                     System.out.println("  Contact Plant Name: " + plantDTO.getContact().getPlantName());
//                 }
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
    
//     private static String readJsonFromResources(String fileName) throws IOException {
//     ClassLoader classLoader = JsonReader.class.getClassLoader();
//     try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
//          Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
//         return scanner.useDelimiter("\\A").next();
//     }
// }
    
// }
