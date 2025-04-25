package com.ronan.mailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextField;


public class EmailSender_WebEDI implements IEmailSender {
     String pythonPath = "C:/Program Files/Python310/python.exe"; // Adjust Python path if needed
    // String scriptPath = "C:/Rohit P/Projects/EDI_PROJECT/email-format/email-format/python/py+java/mailsender.py";
    // String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailsender.py"; // Updated script path
    
    String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailSender_WebEDI.py"; // Updated script path
    String attachmentPath = "\"C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx\""; // Escaped path

     String user_id = "User123";
     String first_name = "John";
     String last_name = "Doe";
    //  String plant_details="2920,2921";

    String mail_body_new=" is now connected with the UD WebEDI Business application.";
    String mail_body_add=" is now connected to an additional UD plant in the WebEDI Business application.";

    String mail_sub_new="UD WebEDI Delivery Schedule and Despatch Advice in production";
    String mail_sub_add="Additional UD WebEDI Delivery Schedule and Despatch Advice in production";
   
     ProcessBuilder processBuilder;

public void sendEmail() {
      
        // String recipient = "rohit.p@capgemini.com";
        // String name = "Rohit";
        // String date = "April 1, 2025";
        String recipient = "rohit.p@capgemini.com";
        String parma_id = "99999";
        String parma_name = "RG Ray Corporation";
        String contacts = "support.edi@udtrucks.com";
        String plant_details="2920,2921";
        plant_details="2920 – UD Trucks Corporation , \r\n" + //
                        "2921 – UD Trucks Corporation , \r\n" + //
                        "2922 – UD Trucks Corporation,  \r\n" + //
                        "2924 – UD Trucks Corporation , \r\n" + //
                        "7876 – Thai-Swedish Assembly Co. Ltd,  \r\n" + //
                        "8374 – UD Trucks Corporation , \r\n" + //
                        "8417 – TMBP Limited , \r\n" + //
                        "8431 – Volvo Group Singapore (Pte) Ltd";
        
        

        // try {
        //     // Fix: Use cmd.exe to execute Python script properly
        //     ProcessBuilder processBuilder = new ProcessBuilder(
        //         "cmd.exe", "/c", pythonPath, scriptPath,
        //         recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath
        //     );
        try {
             startProcess(recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath, plant_details);
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error executing Python script: " + e.getMessage());
        }
    }

    
    // public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details) {
    //     try {
    //         startProcess(supplierEmail, supplierId, supplierName, contact, user_id, first_name, last_name, attachmentPath, plant_details);
    //    } catch (IOException | InterruptedException e) {
    //        System.out.println("❌ Error executing Python script: " + e.getMessage());
    //    }
    
    // }

    private  void startProcess(String recipient,String parma_id,String parma_name, String contacts,String user_id,String first_name,String last_name,String attachmentPath,String plant_details) throws IOException, InterruptedException{
        processBuilder = new ProcessBuilder(
                pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath, plant_details
                    );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("✅ Email process completed.");
    }

   
    // public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact, String user_id,
    //         String plant_details, String supplier_unb, String mail_sub, String mail_body) {
    //             processBuilder = new ProcessBuilder(
    //                 pythonPath, scriptPath,supplierEmail, supplierId, supplierName, contacts, user_id, first_name, last_name, attachmentPath, plant_details
    //                     );
    //             processBuilder.redirectErrorStream(true);
    
    //             Process process = processBuilder.start();
    //             process.waitFor();
    //             System.out.println("✅ Email process completed.");
    // }
    


    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details, String attachment, String mail_sub, String mail_body) {
        try {
            // startProcess(supplierEmail, supplierId, supplierName, contact, plant_details, supplier_unb);
            startProcess(supplierEmail, supplierId, supplierName, contact, plant_details, attachment, mail_sub, mail_body);
       } catch (IOException | InterruptedException e) {
           System.out.println("❌ Error executing Python script: " + e.getMessage());
       }
    }

    public  void startProcess(String recipient,String parma_id,String parma_name, String contacts, String plant_details, String attachment, String mail_sub, String mail_body ) throws IOException, InterruptedException{
        processBuilder = new ProcessBuilder(
                pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, plant_details, attachment, mail_sub, mail_body
                    );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("✅ Email process completed.");
    }

    @Override
    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,
            String plant_details, boolean edi_new) {
       if(edi_new){
             sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details,attachmentPath,mail_sub_new,mail_body_new) ;
        }else{
            sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details,attachmentPath,mail_sub_add,mail_body_add) ;
        }
    }

    public static void main(String[] args) {
        new EmailSender_WebEDI().sendEmail();
    }


    

   

}
