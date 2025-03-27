package com.ronan.entities;

public class INVOICE {
    private PARMA parma;
    private String edifactInvoicD07A;
    private String edifactInvoicD03A;
    private String webEdi;
    private String x12_810;

    public PARMA getPARMA() {
        return parma;
    }

    public void setPARMA(PARMA parma) {
        this.parma = parma;
    }

    public String getEdifactInvoicD07A() {
        return edifactInvoicD07A;
    }

    public void setEdifactInvoicD07A(String edifactInvoicD07A) {
        this.edifactInvoicD07A = edifactInvoicD07A;
    }

    public String getEdifactInvoicD03A() {
        return edifactInvoicD03A;
    }

    public void setEdifactInvoicD03A(String edifactInvoicD03A) {
        this.edifactInvoicD03A = edifactInvoicD03A;
    }

    public String getWebEdi() {
        return webEdi;
    }

    public void setWebEdi(String webEdi) {
        this.webEdi = webEdi;
    }

    public String getX12_810() {
        return x12_810;
    }

    public void setX12_810(String x12_810) {
        this.x12_810 = x12_810;
    }
}
