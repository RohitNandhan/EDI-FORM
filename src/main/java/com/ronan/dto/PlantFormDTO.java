package com.ronan.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ronan.Utils.EDITYPE;
import com.ronan.entities.PARMA;

public class PlantFormDTO {
    private List<PARMA> selectedPlants;
    private EDITYPE ediType;
    private boolean japaneseSupplier;

    // Constructor
    public PlantFormDTO(List<PARMA> selectedPlants, EDITYPE ediType, boolean japaneseSupplier) {
        this.selectedPlants = selectedPlants;
        this.ediType = ediType;
        this.japaneseSupplier = japaneseSupplier;
    }

    public List<PARMA> getSelectedPlants() {
        return selectedPlants;
    }

    public void setSelectedPlants(List<PARMA> selectedPlants) {
        this.selectedPlants = selectedPlants;
    }

    public EDITYPE getEdiType() {
        return ediType;
    }

    public void setEdiType(EDITYPE ediType) {
        this.ediType = ediType;
    }

    public boolean isJapaneseSupplier() {
        return japaneseSupplier;
    }

    public void setJapaneseSupplier(boolean japaneseSupplier) {
        this.japaneseSupplier = japaneseSupplier;
    }

    @Override
    public String toString() {
        return "PlantFormDTO [selectedPlants=" + selectedPlants + ", ediType=" + ediType + ", japaneseSupplier="
                + japaneseSupplier + "]";
    }

    // public String getPlantDetails() {
    //     return "Selected Plants: [ "
        
    //     + "] \n" +
    //            "EDI Type: " + ediType + "\n" +
    //            "Japanese Supplier: " + (japaneseSupplier ? "Yes" : "No");
    // }

    public String getPlantDetails() {
        return "Selected Plants: [ " +
                (selectedPlants != null ? selectedPlants.stream()
                        .map(String::valueOf) // Convert Integer to String
                        .collect(Collectors.joining(", ")) : "") +
                " ]\n" +
                "EDI Type: " + ediType + "\n" +
                "Japanese Supplier: " + (japaneseSupplier ? "Yes" : "No");
    }


    
}
