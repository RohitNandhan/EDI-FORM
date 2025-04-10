package com.ronan.jswing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import com.ronan.Utils.EDITYPE;
import com.ronan.dto.PlantDTO;
import com.ronan.dto.PlantDisplayDTOCopy;
import com.ronan.dto.PlantFormDTO;
import com.ronan.entities.PARMA;
import com.ronan.entities.Plant;
import com.ronan.mailService.EmailSender;
import com.ronan.repositories.JsonReader;

public class EDIImplForm7 {

    private static List<PARMA> parmaList = new ArrayList<>();
    private static boolean isJapanese;
    private static EDITYPE editype;
    private static String supplierId;
    private static String supplierName;
    private static String supplierEmail;
    private static List<PlantDTO> plantInfoList=new ArrayList<>();

    public static void main(String[] args) {
        createForm();
    }

    public static void createForm() {
        // Create Frame
        JFrame frame = new JFrame("EDI IMPL Form");
        
        frame.setSize(450, 350);


        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(70, 130, 180)); // Set title bar color
        titleBar.setPreferredSize(new Dimension(frame.getWidth(), 40));
        titleBar.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("EDI----IMPL Form");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        frame.setLocation(500, 250);
        // frame.setLayout();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        

        // Supplier ID
        JLabel supplierIdLabel = new JLabel("Supplier ID:");
        supplierIdLabel.setBounds(20, 10, 100, 20);
        frame.add(supplierIdLabel);

        JTextField supplierIdField = new JTextField();
        supplierIdField.setBounds(130, 10, 150, 20);
        frame.add(supplierIdField);

        // Supplier Name
        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameLabel.setBounds(20, 40, 100, 20);
        frame.add(supplierNameLabel);

        JTextField supplierNameField = new JTextField();
        supplierNameField.setBounds(130, 40, 150, 20);
        frame.add(supplierNameField);

        // Supplier Email
        JLabel supplierEmailLabel = new JLabel("Supplier Email:");
        supplierEmailLabel.setBounds(20, 70, 100, 20);
        frame.add(supplierEmailLabel);

        JTextField supplierEmailField = new JTextField();
        supplierEmailField.setBounds(130, 70, 200, 20);
        frame.add(supplierEmailField);

        // Select Plants Label
        JLabel plantLabel = new JLabel("Select Plants:");
        plantLabel.setBounds(20, 100, 100, 20);
        frame.add(plantLabel);

        // Checkboxes for Plants
        JCheckBox[] plants = {
            new JCheckBox("2920"), new JCheckBox("2921"),
            new JCheckBox("2922"), new JCheckBox("2924"),
            new JCheckBox("7876"), new JCheckBox("8374"),
            new JCheckBox("8417"), new JCheckBox("8431")
        };

        // Positioning Checkboxes
        int x = 20, y = 120;
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
        japSupplier.setBounds(20, 170, 150, 20);
        frame.add(japSupplier);

        // Drop-down for EDI Type
        JLabel ediLabel = new JLabel("EDI Type:");
        ediLabel.setBounds(20, 200, 100, 20);
        frame.add(ediLabel);

        String[] ediTypes = {"TraditionalEDI", "WebEDI"};
        JComboBox<String> ediDropdown = new JComboBox<>(ediTypes);
        ediDropdown.setBounds(100, 200, 120, 20);
        frame.add(ediDropdown);

        // Submit Button
        // JButton submitButton = new JButton("Submit");
        // submitButton.setBounds(100, 250, 80, 25);
        // frame.add(submitButton);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100, 250, 80, 25);
        frame.add(saveButton);

        // View Info Button
        JButton viewInfoButton = new JButton("View Info");
        viewInfoButton.setBounds(200, 250, 100, 25);
        viewInfoButton.setEnabled(false);
        frame.add(viewInfoButton);

        // Mail Button
        JButton mailButton = new JButton("Send Mail");
        mailButton.setBounds(320, 250, 100, 25);
        mailButton.setEnabled(false);
        frame.add(mailButton);

        // Clear Button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(320, 190, 70, 25);
        frame.add(clearButton);

        // New Mail Button
        // JButton mailButton = new JButton("Send Mail");
        // mailButton.setBounds(290, 250, 100, 25);
        // frame.add(mailButton);


        saveButton.addActionListener(e -> {
            parmaList.clear();
            for (JCheckBox plant : plants) {
                if (plant.isSelected()) {
                    parmaList.add(new PARMA(Integer.parseInt(plant.getText())));
                }
            }
        
            editype = EDITYPE.valueOf(ediDropdown.getSelectedItem().toString());
            isJapanese = japSupplier.isSelected();
            
            // Save supplier details
            supplierId = supplierIdField.getText().trim();
            supplierName = supplierNameField.getText().trim();
            supplierEmail = supplierEmailField.getText().trim();
        
            if (supplierId.isEmpty() || supplierName.isEmpty() || supplierEmail.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter all supplier details.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            viewInfoButton.setEnabled(true);
            mailButton.setEnabled(true);
            saveButton.setEnabled(false);
        
            JOptionPane.showMessageDialog(frame, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // View Info Button Action
        viewInfoButton.addActionListener(e -> {
            if (supplierId == null || supplierId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No data saved! Please save first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            PlantFormDTO plantDetails = new PlantFormDTO(parmaList, editype, isJapanese);
            plantInfoList = JsonReader.execute(plantDetails);
        
            String result = new PlantDisplayDTOCopy().showPlantInfo(plantInfoList, editype, isJapanese);
            showResultDialog(frame, result);
        });

        // Submit Button Action
        // submitButton.addActionListener(e -> {
        //     parmaList.clear();
        //     StringBuilder selectedPlants = new StringBuilder();

        //     for (JCheckBox plant : plants) {
        //         if (plant.isSelected()) {
        //             String pl = plant.getText();
        //             selectedPlants.append(pl).append(", ");
        //             parmaList.add(new PARMA(Integer.parseInt(pl)));
        //         }
        //     }

        //     if (selectedPlants.length() > 0) {
        //         selectedPlants.setLength(selectedPlants.length() - 2);
        //     } else {
        //         selectedPlants.append("None");
        //     }

        //     String type = ediDropdown.getSelectedItem().toString();
        //     editype = EDITYPE.valueOf(type);
        //     isJapanese = japSupplier.isSelected();

        //     // Collect supplier details
        //     supplierId = supplierIdField.getText().trim();
        //     supplierName = supplierNameField.getText().trim();
        //     supplierEmail = supplierEmailField.getText().trim();

        //     if (supplierId.isEmpty() || supplierName.isEmpty() || supplierEmail.isEmpty()) {
        //         JOptionPane.showMessageDialog(frame, "Please enter all supplier details.", "Error", JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     // Create DTO object
        //     PlantFormDTO plantDetails = new PlantFormDTO(parmaList, editype, isJapanese);
        //      plantInfoList = JsonReader.execute(plantDetails);

        //     String result = new PlantDisplayDTOCopy().showPlantInfo(plantInfoList, editype, isJapanese);
        //     showResultDialog(frame, result);
        // });

        // Clear Button Action
        clearButton.addActionListener(e -> {
            for (JCheckBox plant : plants) {
                plant.setSelected(false);
            }
            japSupplier.setSelected(false);
            ediDropdown.setSelectedIndex(0);
            supplierIdField.setText("");
            supplierNameField.setText("");
            supplierEmailField.setText("");
            parmaList.clear();
            isJapanese = false;
            editype = EDITYPE.TraditionalEDI;

            viewInfoButton.setEnabled(false);
            mailButton.setEnabled(false);
            saveButton.setEnabled(true);

            // JOptionPane.showMessageDialog(frame, "Form Cleared!", "Submission Details", JOptionPane.INFORMATION_MESSAGE);
        });

        mailButton.addActionListener(e -> {
            supplierId = supplierIdField.getText().trim();
            supplierName = supplierNameField.getText().trim();
            supplierEmail = supplierEmailField.getText().trim();
            String email = supplierEmailField.getText().trim();
        
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter Supplier Email!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Prompt the user to choose mail format
            String[] options = {"Old Format", "New Format"};
            int choice = JOptionPane.showOptionDialog(frame,
                    "Choose the mail format:",
                    "Select Format",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        
            if (choice == JOptionPane.CLOSED_OPTION) {
                return; // User closed the dialog
            }
        
            StringBuilder contactBuilder = new StringBuilder();
            List<String> plantList = new ArrayList<>();
            List<String> plantDetails = new ArrayList<>();
        
            for (PlantDTO plant : plantInfoList) {
                plantList.add(String.valueOf(plant.getParma()));
                plantDetails.add(plant.getParma() + " – " + plant.getPlantName());
                contactBuilder.append(plant.getContactString());
                contactBuilder.append("; ");
            }
        
            String contact = contactBuilder.toString();
            String plant_details = String.join("; ", plantDetails);
        
            // Send mail based on user's choice
            if (choice == 0) {
                // Old Format
                // EmailSender.sendEmailOld(supplierId, supplierName, supplierEmail, contact, plant_details);
            } else {
                // New Format
                // EmailSender.sendEmailNew(supplierId, supplierName, supplierEmail, contact, plant_details);
            }
        
            JOptionPane.showMessageDialog(frame, "Mail Sent to  " + supplierEmail, "Email Status", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.setVisible(true);
    }

        
    //     // Mail Button Action
    //     mailButton.addActionListener(e -> {
    //         supplierId = supplierIdField.getText().trim();
    //         supplierName = supplierNameField.getText().trim();
    //         supplierEmail = supplierEmailField.getText().trim();
    //         String email = supplierEmailField.getText().trim();
    //         if (email.isEmpty()) {
    //             JOptionPane.showMessageDialog(frame, "Enter Supplier Email!", "Error", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         StringBuilder contactBuilder=new StringBuilder();
    //         List<String> plantList = new ArrayList<>();
    //         List<String> plantDetails = new ArrayList<>();

    //         for(PlantDTO plant:plantInfoList){

    //             // plant_details+=plant.getParma()+plant.getPlantName()+;
    //             plantList.add(String.valueOf(plant.getParma()));
    //             plantDetails.add(plant.getParma() + " – " + plant.getPlantName());
    //             // plantStringBuilder.append();
                
    //             contactBuilder.append(plant.getContactString());
    //             contactBuilder.append("; ");

    //             // contactBuilder.append(String.join("; ", plant.getContactString()));
    //         }
    //         String contact=contactBuilder.toString();
    //         String plant_details=String.join("; ", plantDetails); 
    //         String plant_list=String.join(",", plantList);
    //         // System.out.println(plant_details);   
    
    //         // EmailSender.sendEmail();
    //         EmailSender.sendEmail(supplierId,supplierName,supplierEmail,contact,plant_details);
            
    //         // JOptionPane.showMessageDialog(frame, "Mail Sent to  " + supplierEmail, "Email Status", JOptionPane.INFORMATION_MESSAGE);
    //     });

    //     frame.setVisible(true);
    // }

    // Dialog to show result
    private static JDialog currentDialog;

    private static void showResultDialog(JFrame parent, String result) {
        if (currentDialog != null && currentDialog.isShowing()) {
            currentDialog.dispose();
        }

        JDialog dialog = new JDialog(parent, "Submission Details", true);
        dialog.setSize(500, 300);
        dialog.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setText(result);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(false);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(parent);

        currentDialog = dialog;
        textArea.setText("");
        textArea.append(result);
        dialog.setVisible(true);
    }
}
