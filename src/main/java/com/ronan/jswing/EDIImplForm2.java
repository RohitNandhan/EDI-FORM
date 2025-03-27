package com.ronan.jswing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.ronan.Utils.EDITYPE;
import com.ronan.dto.PlantDTO;
import com.ronan.dto.PlantDisplayDTO;
import com.ronan.dto.PlantFormDTO;
import com.ronan.entities.PARMA;
import com.ronan.repositories.JsonReader;

public class EDIImplForm2 {

    private static  List<PARMA> parmaList=new ArrayList<>();
    private static  boolean isJapanese;
    private static  EDITYPE editype;


    public static void main(String[] args) {
        // Create Frame
        JFrame frame = new JFrame("EDI IMPL Form");
        frame.setSize(380, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Title Label
        JLabel titleLabel = new JLabel("EDI IMPL Form");
        titleLabel.setBounds(120, 10, 200, 20);
        frame.add(titleLabel);

        // Select Plants Label
        JLabel plantLabel = new JLabel("Select Plants :");
        plantLabel.setBounds(20, 40, 100, 20);
        frame.add(plantLabel);

        // Checkboxes for Plants
        JCheckBox[] plants = {
            new JCheckBox("2920"), new JCheckBox("2921"),
            new JCheckBox("2922"), new JCheckBox("2924"),
            new JCheckBox("7878"), new JCheckBox("8374"),
            new JCheckBox("8417"), new JCheckBox("8431")
        };

        // Positioning Checkboxes
        int x = 20, y = 60;
        for (int i = 0; i < plants.length; i++) {
            plants[i].setBounds(x, y, 60, 20);
            frame.add(plants[i]);
            x += 60;
            if (i == 3) { // Move to next row after 4 checkboxes
                x = 20;
                y += 20;
            }
        }

        // Checkbox for Japanese Supplier
        JCheckBox japSupplier = new JCheckBox("Japanese Supplier");
        japSupplier.setBounds(20, 110, 150, 20);
        frame.add(japSupplier);

        // Drop-down for EDI Type
        JLabel ediLabel = new JLabel("EDI Type :");
        ediLabel.setBounds(20, 140, 100, 20);
        frame.add(ediLabel);

        String[] ediTypes = {"TraditionalEDI", "WebEDI"};
        JComboBox<String> ediDropdown = new JComboBox<>(ediTypes);
        ediDropdown.setBounds(100, 140, 120, 20);
        frame.add(ediDropdown);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(180, 180, 80, 25);
        frame.add(submitButton);

        // Clear Button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(270, 180, 70, 25);
        frame.add(clearButton);

        // Submit Button Action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedPlants = new StringBuilder();
                for (JCheckBox plant : plants) {
                    if (plant.isSelected()) {
                        String pl=plant.getText();
                        selectedPlants.append(plant).append(", ");
                        parmaList.add(new PARMA(Integer.parseInt(pl)));
                    }
                }

                if (selectedPlants.length() > 0) {
                    selectedPlants.setLength(selectedPlants.length() - 2); // Remove last comma
                } else {
                    selectedPlants.append("None");
                }

                String type=ediDropdown.getSelectedItem().toString();
                editype = EDITYPE.valueOf(type);
                isJapanese= japSupplier.isSelected();

                // Create DTO object
                PlantFormDTO plantDetails = new PlantFormDTO(parmaList,editype,isJapanese);
                // createDTO(plantDetails);

                // Call getPlantDetails() method and show resul

                
               List<PlantDTO> plantInfoList= JsonReader.execute(plantDetails);
                
              String Result= PlantDisplayDTO.showPlantInfo(plantInfoList);

                JOptionPane.showMessageDialog(frame, Result, "Submission Details", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });

        // Clear Button Action
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox plant : plants) {
                    plant.setSelected(false);
                }
                japSupplier.setSelected(false);
                ediDropdown.setSelectedIndex(0);
                parmaList=new ArrayList<>();
                isJapanese=false;
                editype=EDITYPE.TraditionalEDI;
                JOptionPane.showMessageDialog(frame, "All selections cleared!", "Reset Confirmation", JOptionPane.INFORMATION_MESSAGE);
          
            }
        });

        // Set Frame Visible
        frame.setVisible(true);
    }

    // checking demo , delete later
    // public PlantFormDTO createDTO(String plants, String ediType, Boolean isJapanese){
        
    //     int plants[]=
    //     System.out.println("plants:"+plants);
    //     System.out.println("editype:"+ediType);

    //     return null;

    // }

    public static PlantFormDTO createDTO(PlantFormDTO plantFormDTO){

        System.out.println(plantFormDTO.getPlantDetails());

        return null;

    }
   

}
