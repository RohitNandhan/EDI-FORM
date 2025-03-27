package com.ronan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class PlantDTO {

    @JsonProperty("Parma")
    private int parma;

    @JsonProperty("DESADV")
    private List<Desadv> desadv;

    @JsonProperty("INVOICE")
    private Map<String, String> invoice;

    @JsonProperty("Contact")
    private Contact contact;

    // Getters and Setters
    public int getParma() {
        return parma;
    }

    public void setParma(int parma) {
        this.parma = parma;
    }

    public List<Desadv> getDesadv() {
        return desadv;
    }

    public void setDesadv(List<Desadv> desadv) {
        this.desadv = desadv;
    }

    public Map<String, String> getInvoice() {
        return invoice;
    }

    public void setInvoice(Map<String, String> invoice) {
        this.invoice = invoice;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PlantDTO{" +
                "parma=" + parma +
                ", desadv=" + desadv +
                ", invoice=" + invoice +
                ", contact=" + contact +
                '}';
    }
}
