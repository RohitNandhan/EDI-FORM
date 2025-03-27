package com.ronan.entities;

 public class DESADV2 {
    private PARMA parma;
    private String genericMessage;
    private String additionalInternalReceiverMessageType;
    private String additionalInternalReceiverMessageVersion;
    private String comments;

    public PARMA getPARMA() {
        return parma;
    }

    public void setPARMA(PARMA parma) {
        this.parma = parma;
    }

    public String getGenericMessage() {
        return genericMessage;
    }

    public void setGenericMessage(String genericMessage) {
        this.genericMessage = genericMessage;
    }

    public String getAdditionalInternalReceiverMessageType() {
        return additionalInternalReceiverMessageType;
    }

    public void setAdditionalInternalReceiverMessageType(String additionalInternalReceiverMessageType) {
        this.additionalInternalReceiverMessageType = additionalInternalReceiverMessageType;
    }

    public String getAdditionalInternalReceiverMessageVersion() {
        return additionalInternalReceiverMessageVersion;
    }

    public void setAdditionalInternalReceiverMessageVersion(String additionalInternalReceiverMessageVersion) {
        this.additionalInternalReceiverMessageVersion = additionalInternalReceiverMessageVersion;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
