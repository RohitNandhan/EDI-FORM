package com.ronan.dto;

import java.util.List;
import java.util.Map;

import com.ronan.Utils.EDITYPE;

public class PlantDisplayDTOCopy {

     StringBuilder sb ;

     public PlantDisplayDTOCopy(){
        sb=new StringBuilder();
     }
    
 
public  String showPlantInfo(List<PlantDTO> plantInfoList,EDITYPE type, boolean isJapanese) {
    // Convert list to string representation
    // StringBuilder sb = new StringBuilder();
    String country = "";
   country= (isJapanese) ? "Japanese" : "Non-Japanese" ;
    
    for (PlantDTO plant : plantInfoList) {
        sb.append("\n");
        addDesadv(plant,country);
        // Append Invoice details
        // sb.append("\n");
        addInvoice(plant, type,isJapanese);
        sb.append("\n");
        // Append Contact details
        addContact(plant,isJapanese);
        sb.append("\n");
        sb.append("\n====================================================================\n");
    }




    return sb.toString();
}

public  void addDesadv(PlantDTO plant, String country){sb.append("Parma: ").append(plant.getParma()).append("\n\n");

// Append DESADV details
sb.append("DESADV:\n");
if (plant.getDesadv() != null) {
    for (Desadv desadv : plant.getDesadv()) {
        if( desadv.getComments().equals(country) | desadv.getComments()=="both" ){
        sb.append("    Generic Message: ").append(desadv.getGenericMessage()).append("\n")
          .append("    Additional Receiver Type: ").append(desadv.getAdditionalInternalReceiverMessageType()).append("\n")
          .append("    Additional Receiver Version: ").append(desadv.getAdditionalInternalReceiverMessageVersion()).append("\n")
        //   .append("    Comments: ").append(desadv.getComments())
          .append("\n");
    }
}
} else {
    sb.append("  No DESADV data\n");
}
}
public void addInvoice(PlantDTO plant, EDITYPE type, boolean isJapanese){
    sb.append("INVOICE:\n");
        
        if (!isJapanese && plant.getInvoice() != null && !plant.getInvoice().isEmpty()) {
            for (Map.Entry<String, String> entry : plant.getInvoice().entrySet()) {
                if(type==EDITYPE.WebEDI){
                if (entry.getKey().contains("WEB")) { // Filter keys containing "WEB"
                    sb.append("  - ").append(entry.getKey()).append(" :-    ").append(entry.getValue()).append("\n");
                }
            }else{
                if (!entry.getKey().contains("WEB")) { // Filter keys containing "Traditional"
                    sb.append("  - ").append(entry.getKey()).append(" :-    ").append(entry.getValue()).append("\n");
                }
            }
            }
        } else {
            sb.append("  No Invoice data\n");
        }

}
public  void addContact(PlantDTO plant, boolean isJapanese){
    if (plant.getContact() != null) {
        sb.append("Contact:\n")
          .append("  Plant Name: ").append(plant.getContact().getPlantName()).append("\n")
          .append("  Emails:").append("\n   ").append(String.join("; ", plant.getContact().getEmails())).append("\n");
          if(!isJapanese)
          sb.append("\n  Invoice Emails: ").append("\n   ").append(String.join("; ", plant.getContact().getInvoiceEmails())).append("\n");
    } else {
        sb.append("  No Contact details\n");
    }
}
}

// public static String showJapanesePlantInfo(List<PlantDTO> plantInfoList) {
//     // Convert list to string representation
    
    
//     for (PlantDTO plant : plantInfoList) {
//         sb.append("Parma: ").append(plant.getParma()).append("\n");

//         // Append DESADV details
//         sb.append("DESADV:\n");
//         if (plant.getDesadv() != null) {
//             for (Desadv desadv : plant.getDesadv()) {
//                 if(desadv.getComments().equals("Japanese") | desadv.getComments()=="both" ){
//                 sb.append("  - Generic Message: ").append(desadv.getGenericMessage()).append("\n")
//                   .append("    Additional Receiver Type: ").append(desadv.getAdditionalInternalReceiverMessageType()).append("\n")
//                   .append("    Additional Receiver Version: ").append(desadv.getAdditionalInternalReceiverMessageVersion()).append("\n")
//                   .append("\n");
//             }
//             }
//         } else {
//             sb.append("  No DESADV data\n");
//         }

//         // Append Invoice details
//         // sb.append("INVOICE:\n");
//         // if (plant.getInvoice() != null && !plant.getInvoice().isEmpty()) {
//         //     for (Map.Entry<String, String> entry : plant.getInvoice().entrySet()) {
//         //         sb.append("  - ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
//         //     }
//         // } else {
//         //     sb.append("  No Invoice data\n");
//         // }

//         // Append Contact details
//         if (plant.getContact() != null) {
//             sb.append("Contact:\n")
//               .append("  Plant Name: ").append(plant.getContact().getPlantName()).append("\n")
//               .append("  Emails: \n").append(String.join("; ", plant.getContact().getEmails())).append("\n")
//             //   .append("  Invoice Emails: ").append(String.join(", ", plant.getContact().getInvoiceEmails()))
//               .append("\n");
//         } else {
//             sb.append("  No Contact details\n");
//         }

//         sb.append("\n---------------------------------------\n");
//     }




//     return sb.toString();
// }

// public static String showNonJapanesePlantInfo(List<PlantDTO> plantInfoList, EDITYPE type) {
//     // Convert list to string representation
//     StringBuilder sb = new StringBuilder();
    
//     for (PlantDTO plant : plantInfoList) {
//         sb.append("Parma: ").append(plant.getParma()).append("\n");

//         // Append DESADV details
//         sb.append("DESADV:\n");
//         if (plant.getDesadv() != null) {
//             for (Desadv desadv : plant.getDesadv()) {
//                 if( desadv.getComments().equals("Non-Japanese") | desadv.getComments()=="both" ){
//                 sb.append("  - Generic Message: ").append(desadv.getGenericMessage()).append("\n")
//                   .append("    Additional Receiver Type: ").append(desadv.getAdditionalInternalReceiverMessageType()).append("\n")
//                   .append("    Additional Receiver Version: ").append(desadv.getAdditionalInternalReceiverMessageVersion()).append("\n")
//                 //   .append("    Comments: ").append(desadv.getComments())
//                   .append("\n");
//             }
//         }
//         } else {
//             sb.append("  No DESADV data\n");
//         }

//         // Append Invoice details
//         sb.append("INVOICE:\n");
        
//         if (plant.getInvoice() != null && !plant.getInvoice().isEmpty()) {
//             for (Map.Entry<String, String> entry : plant.getInvoice().entrySet()) {
//                 if(type==EDITYPE.WebEDI){
//                 if (entry.getKey().contains("WEB")) { // Filter keys containing "WEB"
//                     sb.append("  - ").append(entry.getKey()).append(" :-    ").append(entry.getValue()).append("\n");
//                 }
//             }else{
//                 if (!entry.getKey().contains("WEB")) { // Filter keys containing "Traditional"
//                     sb.append("  - ").append(entry.getKey()).append(" :-    ").append(entry.getValue()).append("\n");
//                 }
//             }
//             }
//         } else {
//             sb.append("  No Invoice data\n");
//         }

//         // Append Contact details
//         if (plant.getContact() != null) {
//             sb.append("Contact:\n")
//               .append("  Plant Name: ").append(plant.getContact().getPlantName()).append("\n")
//               .append("  Emails: ").append(String.join(", ", plant.getContact().getEmails())).append("\n")
//               .append("  Invoice Emails: ").append(String.join(", ", plant.getContact().getInvoiceEmails())).append("\n");
//         } else {
//             sb.append("  No Contact details\n");
//         }

//         sb.append("\n---------------------------------------\n");
//     }




//     return sb.toString();
// }

// public static String showNonJapanesePlantInfo(List<PlantDTO> plantInfoList, EDITYPE type, boolean isJapanese) {
//     // Convert list to string representation
//     StringBuilder sb = new StringBuilder();
    
//     for (PlantDTO plant : plantInfoList) {
//         sb.append("Parma: ").append(plant.getParma()).append("\n");

//         // Append DESADV details
//         sb.append("DESADV:\n");
//         if (plant.getDesadv() != null) {
            
//             for (Desadv desadv : plant.getDesadv()) {
//                 if(!isJapanese){
//                 if( desadv.getComments().equals("Non-Japanese") | desadv.getComments()=="both" ){
//                 sb.append("  - Generic Message: ").append(desadv.getGenericMessage()).append("\n")
//                   .append("    Additional Receiver Type: ").append(desadv.getAdditionalInternalReceiverMessageType()).append("\n")
//                   .append("    Additional Receiver Version: ").append(desadv.getAdditionalInternalReceiverMessageVersion()).append("\n")
//                 //   .append("    Comments: ").append(desadv.getComments())
//                   .append("\n");
//             }
//         }else{

//         }
//         }
//         } else {
//             sb.append("  No DESADV data\n");
//         }

//         // Append Invoice details
//         sb.append("INVOICE:\n");
        
//         if (plant.getInvoice() != null && !plant.getInvoice().isEmpty()) {
//             for (Map.Entry<String, String> entry : plant.getInvoice().entrySet()) {
//                 if(type==EDITYPE.WebEDI){
//                 if (entry.getKey().contains("WEB")) { // Filter keys containing "WEB"
//                     sb.append("  - ").append(entry.getKey()).append(" :-    ").append(entry.getValue()).append("\n");
//                 }
//             }else{
//                 if (!entry.getKey().contains("WEB")) { // Filter keys containing "Traditional"
//                     sb.append("  - ").append(entry.getKey()).append(" :-    ").append(entry.getValue()).append("\n");
//                 }
//             }
//             }
//         } else {
//             sb.append("  No Invoice data\n");
//         }

//         // Append Contact details
//         if (plant.getContact() != null) {
//             sb.append("Contact:\n")
//               .append("  Plant Name: ").append(plant.getContact().getPlantName()).append("\n")
//               .append("  Emails: ").append(String.join(", ", plant.getContact().getEmails())).append("\n")
//               .append("  Invoice Emails: ").append(String.join(", ", plant.getContact().getInvoiceEmails())).append("\n");
//         } else {
//             sb.append("  No Contact details\n");
//         }

//         sb.append("\n---------------------------------------\n");
//     }




//     return sb.toString();
// }

//     public static String showPlantInfo(List<PlantDTO> plantInfoList, boolean isJapanese, EDITYPE type) {
//        if(isJapanese){
//             return showJapanesePlantInfo(plantInfoList);
//        }else{
//             return showNonJapanesePlantInfo(plantInfoList, type);
//             // return showNonJapanesePlantInfo(plantInfoList, type, isJapanese);
//        }
//     //    showPlantInfo(plantInfoList);
//     }

// }




