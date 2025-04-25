package com.ronan.mailService;

public interface IEmailSender {


    public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details, boolean edi_new);

    // public void sendEmail(String supplierId, String supplierName, String supplierEmail, String contact,String plant_details, String supplier_unb, String mail_sub, String mail_body);
     

     
}
