package com.ronan.mailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JTextField;


public class EmailSender_EDI implements IEmailSender {

    static String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailSender_IEDI.py"; // Updated script path
    
   
            String supplier_unb="000008030001043599:30:002920:DEFAULT";

                // String edi_subject="NEWW";
            String mail_body_new="The <b>UD EDI DELFOR</b> and <b>DESADV</b> is now set up for the below relation";
            String mail_body_add="The additional <b>UD EDI DELFOR</b> and <b>DESADV</b> is now set up for the below relation.";

            String mail_sub_new="UD EDI DELFOR and DESADV in production";
            String mail_sub_add="Additional UD EDI DELFOR and DESADV in production";

            String mail_sub="";
            String mail_body="";

       
    String invoic_template="C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/resources/mail templates/UD EDI DELFOR and DESADV in production request to test invoic.html";
    String non_invoic_template="C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/resources/mail templates/UD EDI DELFOR and DESADV in production.html";
    
    String template_path=non_invoic_template;

     String plantList;

       
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

    public void startProcess(String recipient,String parma_id,String parma_name, String contacts, String plant_details, String supplier_unb, String mail_sub, String mail_body ) throws IOException, InterruptedException{
        processBuilder = new ProcessBuilder(
                pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, plant_details, supplier_unb, mail_sub, mail_body, template_path, plantList
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
            String plant_details, boolean edi_new, boolean invoic) {
                
       if(edi_new){
            mail_body=mail_body_new;
            mail_sub=mail_sub_new;
            // sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, supplier_unb,mail_sub_new , mail_body_new);
        }else{
            mail_body=mail_body_add;
            mail_sub=mail_sub_add;
            // sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, supplier_unb,mail_sub_add , mail_body_add);
        }    
        if(invoic){
            template_path=invoic_template;
            mail_sub=mail_sub+", request to test invoic";
        }else{
            template_path=non_invoic_template;
        }
        
        sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, supplier_unb,mail_sub , mail_body);
        
    }


    @Override
    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,
            String plant_details, boolean edi_new, boolean invoic, String plantList) {
        
                this.plantList=plantList;
                sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, edi_new, invoic);
    }

}
