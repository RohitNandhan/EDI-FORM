package com.ronan.mailService;

import java.util.List;

public interface IEmailSender {

    String pythonPath = "C:/Program Files/Python310/python.exe";
    static String attachmentPath = "\"C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx\""; // Escaped path


    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details, boolean edi_new, boolean invoic);

    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,
            String plant_details, boolean edi_new, boolean invoic, String plantList);

    // public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details, String supplier_unb, String mail_sub, String mail_body);
     
    default void createMail(){
        String TemplatePath="Create ID for new Web EDI user.html";
        String recipient="DBS@udtrucks.com";

     }

     
}
