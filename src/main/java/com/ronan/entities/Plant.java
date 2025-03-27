package com.ronan.entities;

import java.util.List;


public class Plant{
    
    private int plantId;
    // private String plantName;
    private String DESADV_String;
    private String INVOICE_String;
    private List<String> contacts;
    private List<String> invoice_contacts;

    public Plant(){

    }

    public Plant(String DESADV_String, String INVOICE_String, List<String> contacts, List<String> invoice_contacts, int plantId) {
        this.DESADV_String = DESADV_String;
        this.INVOICE_String = INVOICE_String;
        this.contacts = contacts;
        this.invoice_contacts = invoice_contacts;
        this.plantId = plantId;
    }
    
    public int getPlantId() {
        return plantId;
    }
    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }
    public String getDESADV_String() {
        return DESADV_String;
    }
    public void setDESADV_String(String dESADV_String) {
        DESADV_String = dESADV_String;
    }
    public String getINVOICE_String() {
        return INVOICE_String;
    }
    public void setINVOICE_String(String iNVOICE_String) {
        INVOICE_String = iNVOICE_String;
    }
    public List<String> getContacts() {
        return contacts;
    }
    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }
    public List<String> getInvoice_contacts() {
        return invoice_contacts;
    }
    public void setInvoice_contacts(List<String> invoice_contacts) {
        this.invoice_contacts = invoice_contacts;
    }
    @Override
    public String toString() {
        return "Plant [plantId=" + plantId + ", DESADV_String=" + DESADV_String + ", INVOICE_String=" + INVOICE_String
                + ", contacts=" + contacts + ", invoice_contacts=" + invoice_contacts + "]";
    }

}