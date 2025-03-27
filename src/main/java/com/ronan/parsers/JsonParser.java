// package com.ronan.parsers;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.ronan.entities.Plant;

// public class JsonParser {

//     public static List<Plant> parseJson(String jsonData) throws IOException {
//         ObjectMapper objectMapper = new ObjectMapper();
//         JsonNode rootNode = objectMapper.readTree(jsonData);
//         List<Plant> plantList = new ArrayList<>();

//         for (JsonNode plantNode : rootNode) {
//             int plantId = plantNode.get("Parma").asInt();
//             Plant plant = findOrCreatePlant(plantList, plantId);

//             // Parsing DESADV
//             JsonNode desadvArray = plantNode.get("DESADV");
//             if (desadvArray != null && desadvArray.isArray()) {
//                 // List<String> desadvList = new ArrayList<>();
//                 // for (JsonNode desadvNode : desadvArray) {
//                 //     desadvList.add(desadvNode.asText());
//                 // }
//                 plant.setDESADV_String(desadvArray.asText());
//             }

//             // Parsing INVOICE
//             JsonNode invoiceNode = plantNode.get("INVOICE");
//             if (invoiceNode != null && invoiceNode.isObject()) {
//                 // Map<String, String> invoiceMap = new HashMap<>();
//                 // invoiceNode.fields().forEachRemaining(entry -> invoiceMap.put(entry.getKey(), entry.getValue().asText()));
//                 // plant.setINVOICE_Map(invoiceMap);
//                 plant.setINVOICE_String(invoiceNode.asText());
//             }

//             // Parsing Contact
//             JsonNode contactNode = plantNode.get("Contact");
//             if (contactNode != null) {
//                // plant.setPlantName(contactNode.get("Plant Name").asText());
//                 plant.setContacts(objectMapper.convertValue(contactNode.get("Emails"), new TypeReference<List<String>>() {}));
//                 plant.setInvoice_contacts(objectMapper.convertValue(contactNode.get("Invoice Emails"), new TypeReference<List<String>>() {}));
//             }
//         }
//         return plantList;
//     }
//     private static Plant findOrCreatePlant(List<Plant> plantList, int plantId) {
//         for (Plant plant : plantList) {
//             if (plant.getPlantId() == plantId) {
//                 return plant;
//             }
//         }
//         Plant newPlant = new Plant();
//         newPlant.setPlantId(plantId);
//         plantList.add(newPlant);
//         return newPlant;
//     }
// }
// }

