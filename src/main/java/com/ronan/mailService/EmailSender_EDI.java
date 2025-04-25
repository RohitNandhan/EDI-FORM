package com.ronan.mailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextField;


public class EmailSender_EDI implements IEmailSender {
    static String pythonPath = "C:/Program Files/Python310/python.exe"; // Adjust Python path if needed
    // String scriptPath = "C:/Rohit P/Projects/EDI_PROJECT/email-format/email-format/python/py+java/mailsender.py";
    // String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailsender.py"; // Updated script path
    
   static String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailSender_EDI.py"; // Updated script path
   static String attachmentPath = "\"C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx\""; // Escaped path

    String supplier_unb="000008030001043599:30:002920:DEFAULT";

        // String edi_subject="NEWW";
        String mail_body_new="The <b>UD EDI DELFOR</b> and <b>DESADV</b> is now set up for the below relation";
        String mail_body_add="The additional <b>UD EDI DELFOR</b> and <b>DESADV</b> is now set up for the below relation.";

       String mail_sub_new="UD EDI DELFOR and DESADV in production, request to test invoic";
       String mail_sub_add="Additional UD EDI DELFOR and DESADV in production";
       
    // static String user_id = "User123";
    // static String first_name = "John";
    // static String last_name = "Doe";
    // static String plant_details="2920,2921";
   
    static ProcessBuilder processBuilder;

public void sendEmail() {
      
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

        

        //The additional UD EDI DELFOR and DESADV is now set up for the below relation.
        // 
        

        // try {
        //     // Fix: Use cmd.exe to execute Python script properly
        //     ProcessBuilder processBuilder = new ProcessBuilder(
        //         "cmd.exe", "/c", pythonPath, scriptPath,
        //         recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath
        //     );
        try {
             startProcess(recipient, parma_id, parma_name, contacts, plant_details, supplier_unb,mail_sub_add, mail_body_add);
            //  startProcess(recipient, parma_id, parma_name, contacts, plant_details, supplier_unb, edi_body,edi_subject);
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error executing Python script: " + e.getMessage());
        }
    }

    // send_email(recipient, parma_id, parma_name, contacts,plant_details, supplier_unb)
    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details, String supplier_unb, String mail_sub, String mail_body) {
        try {
            // startProcess(supplierEmail, supplierId, supplierName, contact, plant_details, supplier_unb);
            startProcess(supplierEmail, supplierId, supplierName, contact, plant_details, supplier_unb, mail_sub,mail_body);
       } catch (IOException | InterruptedException e) {
           System.out.println("❌ Error executing Python script: " + e.getMessage());
       }
    }

    public static void startProcess(String recipient,String parma_id,String parma_name, String contacts, String plant_details, String supplier_unb, String mail_sub, String mail_body ) throws IOException, InterruptedException{
        processBuilder = new ProcessBuilder(
                pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, plant_details, supplier_unb, mail_sub, mail_body
                    );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("✅ Email process completed.");
    }

    public static void main(String[] args) {
        new EmailSender_EDI().sendEmail();
    }

    @Override
    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,
            String plant_details, boolean edi_new) {
       if(edi_new){
            sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, supplier_unb,mail_sub_new , mail_body_new);
        }else{
            sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, supplier_unb,mail_sub_add , mail_body_add);
        }
    }

}
