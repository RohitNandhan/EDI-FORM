package com.ronan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Desadv {

    @JsonProperty("Generic Message")
    private String genericMessage;

    @JsonProperty("Additional Internal Receiver Message Type")
    private Object additionalInternalReceiverMessageType; 

    @JsonProperty("Additional Internal Receiver Message Version")
    private Object additionalInternalReceiverMessageVersion; 

    @JsonProperty("Comments")
    private String comments="both";

    // Getters and Setters
    public String getGenericMessage() {
        return genericMessage;
    }

    public void setGenericMessage(String genericMessage) {
        this.genericMessage = genericMessage;
    }

    public Object getAdditionalInternalReceiverMessageType() {
        return additionalInternalReceiverMessageType;
    }

    // public Object getAdditionalInternalReceiverMessageType(boolean isJapanese) {
     
    //     return (isJapanese) ? additionalInternalReceiverMessageType : "";
    // }

    public void setAdditionalInternalReceiverMessageType(Object additionalInternalReceiverMessageType) {
        this.additionalInternalReceiverMessageType = additionalInternalReceiverMessageType;
    }

    public Object getAdditionalInternalReceiverMessageVersion() {
        return additionalInternalReceiverMessageVersion;
    }

    public void setAdditionalInternalReceiverMessageVersion(Object additionalInternalReceiverMessageVersion) {
        this.additionalInternalReceiverMessageVersion = additionalInternalReceiverMessageVersion;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Desadv{" +
                "genericMessage='" + genericMessage + '\'' +
                ", additionalInternalReceiverMessageType=" + additionalInternalReceiverMessageType +
                ", additionalInternalReceiverMessageVersion=" + additionalInternalReceiverMessageVersion +
                ", comments='" + comments + '\'' +
                '}';
    }
}
