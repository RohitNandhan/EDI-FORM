package com.ronan.mailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JTextField;


public class EmailSender_WebEDI implements IEmailSender {
    //  String pythonPath = "C:/Program Files/Python310/python.exe"; // Adjust Python path if needed
    // String scriptPath = "C:/Rohit P/Projects/EDI_PROJECT/email-format/email-format/python/py+java/mailsender.py";
    // String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailsender.py"; // Updated script path
    
    String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailSender_IWebEDI.py"; // Updated script path
    // String attachmentPath = "\"C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx\""; // Escaped path


    String mail_body="";
    String mail_sub="";
    String mail_body_new=" is now connected with the UD WebEDI Business application.";
    String mail_body_add=" is now connected to an additional UD plant in the WebEDI Business application.";

    String mail_sub_new="UD WebEDI Delivery Schedule and Despatch Advice in production";
    String mail_sub_add="Additional UD WebEDI Delivery Schedule and Despatch Advice in production";

    String non_invoic_template="C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/resources/mail templates/Additional UD WebEDI Delivery Schedule and Despatch Advice in production2.html";
    String invoic_template="C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/resources/mail templates/UD WebEDI + Invoice.html";
    
    String template_path=invoic_template;

    String plantList;

     ProcessBuilder processBuilder;
/* 
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
        **/

    

    // private  void startProcess(String recipient,String parma_id,String parma_name, String contacts,String user_id,String first_name,String last_name,String attachmentPath,String plant_details) throws IOException, InterruptedException{
    //     processBuilder = new ProcessBuilder(
    //             pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath, plant_details
    //                 );
    //         processBuilder.redirectErrorStream(true);

    //         Process process = processBuilder.start();
    //         process.waitFor();
    //         System.out.println("✅ Email process completed.");
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
                pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, plant_details, attachment, mail_sub, mail_body, template_path, plantList
                    );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("✅ Email process completed.");
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
                }else{
                    template_path=non_invoic_template;
                }
        sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, attachmentPath,mail_sub , mail_body);
    }    
    

    // for creating new user mail
    public void createMail(){
        String TemplatePath="C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/resources/mail templates/Create ID for new Web EDI user.html";
       
        String recipient="DBS@udtrucks.com";
        String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailSender.py"; // Updated script path
   
        try {
            startProcess(scriptPath,recipient, TemplatePath);
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error executing Python script: " + e.getMessage());
        } 
    }

    public  void startProcess(String scriptPath, String recipient, String templatePath ) throws IOException, InterruptedException{
        processBuilder = new ProcessBuilder(
                pythonPath, scriptPath, recipient,templatePath );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("✅ create user Email process completed.");
    }


    @Override
    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,
            String plant_details, boolean edi_new, boolean invoic, String plantList) {
       
                this.plantList=plantList;
                sendEmail(supplierId, supplierName, supplierEmail, contact, plant_details, edi_new, invoic);
    }


    // public static void main(String[] args) {
    //     new EmailSender_WebEDI().createMail();
    // }
    

   

}
