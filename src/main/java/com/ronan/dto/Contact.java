package com.ronan.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {

    @JsonProperty("Plant Name")
    private String plantName;

    @JsonProperty("Emails")
    private List<String> emails;
   

    @JsonProperty("Invoice Emails")
    private List<String> invoiceEmails;

    // Getters and Setters
    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public String getEmailString(){
        return emails.stream().collect(Collectors.joining(";"));
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    // public void setEmails(List<String> emails) {
    //     this.emails = emails;
    // }

    public List<String> getInvoiceEmails() {
        return invoiceEmails;
    }

    public void setInvoiceEmails(List<String> invoiceEmails) {
        this.invoiceEmails = invoiceEmails;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "plantName='" + plantName + '\'' +
                ", emails=" + emails +
                ", invoiceEmails=" + invoiceEmails +
                '}';
    }
}
