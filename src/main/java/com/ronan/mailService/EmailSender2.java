package com.ronan.mailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextField;


public class EmailSender2 {
    static String pythonPath = "C:/Program Files/Python310/python.exe"; // Adjust Python path if needed
    // String scriptPath = "C:/Rohit P/Projects/EDI_PROJECT/email-format/email-format/python/py+java/mailsender.py";
    // String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailsender.py"; // Updated script path
    
   static String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailSender_EDI.py"; // Updated script path
   static String attachmentPath = "\"C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx\""; // Escaped path

    // static String user_id = "User123";
    // static String first_name = "John";
    // static String last_name = "Doe";
    // static String plant_details="2920,2921";
   
    static ProcessBuilder processBuilder;

public static void sendEmail() {
      
        // String recipient = "rohit.p@capgemini.com";
        // String name = "Rohit";
        // String date = "April 1, 2025";
        String recipient = "rohit.p@capgemini.com";
        String parma_id = "99999";
        String parma_name = "Ronan";
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

        String supplier_unb="000008030001043599:30:002920:DEFAULT";

        String edi_subject="NEWW";
        String edi_body="Hi  this NEW";
        
        

        // try {
        //     // Fix: Use cmd.exe to execute Python script properly
        //     ProcessBuilder processBuilder = new ProcessBuilder(
        //         "cmd.exe", "/c", pythonPath, scriptPath,
        //         recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath
        //     );
        try {
             startProcess(recipient, parma_id, parma_name, contacts, plant_details, supplier_unb);
            //  startProcess(recipient, parma_id, parma_name, contacts, plant_details, supplier_unb, edi_body,edi_subject);
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error executing Python script: " + e.getMessage());
        }
    }

    // send_email(recipient, parma_id, parma_name, contacts,plant_details, supplier_unb)
    public static void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details, String supplier_unb, String edi_body, String edi_subject) {
        try {
            startProcess(supplierEmail, supplierId, supplierName, contact, plant_details, supplier_unb);
       } catch (IOException | InterruptedException e) {
           System.out.println("❌ Error executing Python script: " + e.getMessage());
       }
    
    }

    public static void startProcess(String recipient,String parma_id,String parma_name, String contacts, String plant_details, String supplier_unb) throws IOException, InterruptedException{
        processBuilder = new ProcessBuilder(
                pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, plant_details, supplier_unb
                    );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("✅ Email process completed.");
    }

    public static void main(String[] args) {
        sendEmail();
    }

   

}
