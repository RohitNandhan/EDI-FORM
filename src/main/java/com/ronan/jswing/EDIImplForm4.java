package com.ronan.jswing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.ronan.Utils.EDITYPE;
import com.ronan.dto.PlantDTO;
import com.ronan.dto.PlantDisplayDTOCopy;
import com.ronan.dto.PlantFormDTO;
import com.ronan.entities.PARMA;
import com.ronan.repositories.JsonReader;

public class EDIImplForm4 {

    private static List<PARMA> parmaList = new ArrayList<>();
    private static boolean isJapanese;
    private static EDITYPE editype;

    public static void main(String[] args) {
        createForm();
    }

    public static void createForm() {
        // Create Frame
        JFrame frame = new JFrame("EDI IMPL Form");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Title Label
        JLabel titleLabel = new JLabel("EDI IMPL Form");
        titleLabel.setBounds(140, 10, 200, 20);
        frame.add(titleLabel);

        // Select Plants Label
        JLabel plantLabel = new JLabel("Select Plants:");
        plantLabel.setBounds(20, 40, 100, 20);
        frame.add(plantLabel);

        // Checkboxes for Plants
        JCheckBox[] plants = {
            new JCheckBox("2920"), new JCheckBox("2921"),
            new JCheckBox("2922"), new JCheckBox("2924"),
            new JCheckBox("7876"), new JCheckBox("8374"),
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
        JLabel ediLabel = new JLabel("EDI Type:");
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
                parmaList.clear(); // Ensure it's fresh every time
                StringBuilder selectedPlants = new StringBuilder();

                for (JCheckBox plant : plants) {
                    if (plant.isSelected()) {
                        String pl = plant.getText();
                        selectedPlants.append(pl).append(", ");
                        parmaList.add(new PARMA(Integer.parseInt(pl)));
                    }
                }

                if (selectedPlants.length() > 0) {
                    selectedPlants.setLength(selectedPlants.length() - 2); // Remove last comma
                } else {
                    selectedPlants.append("None");
                }

                String type = ediDropdown.getSelectedItem().toString();
                editype = EDITYPE.valueOf(type);
                isJapanese = japSupplier.isSelected();

                // Create DTO object
                PlantFormDTO plantDetails = new PlantFormDTO(parmaList, editype, isJapanese);
                List<PlantDTO> plantInfoList = JsonReader.execute(plantDetails);

                String result = new PlantDisplayDTOCopy().showPlantInfo(plantInfoList,editype,isJapanese);
                // String result = PlantDisplayDTOCopy.showPlantInfo(plantInfoList,editype,isJapanese);

                // Show the result in a new dialog with scroll and close button
                showResultDialog(frame, result);
                // result="";
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
                parmaList.clear();
                isJapanese = false;
                editype = EDITYPE.TraditionalEDI;

                JOptionPane.showMessageDialog(frame, "Form Cleared!", "Submission Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Set Frame Visible
        frame.setVisible(true);
    }

    /**
     * Displays the result in a new dialog with a horizontal scroll bar and a close button.
    //  */
    private static JDialog currentDialog; // Track the current open dialog

private static void showResultDialog(JFrame parent, String result) {
    // Dispose of the previous dialog if it's open
    if (currentDialog != null && currentDialog.isShowing()) {
        currentDialog.dispose();
    }

    // Create a new Dialog
    JDialog dialog = new JDialog(parent, "Submission Details", true);
    dialog.setSize(500, 300);
    dialog.setLayout(new BorderLayout());

    // Create a new Text Area for each submission
    JTextArea textArea = new JTextArea();
    textArea.setText(result); // Set new result
    textArea.setLineWrap(false); // Disable line wrap for horizontal scrolling
    textArea.setWrapStyleWord(false);
    textArea.setEditable(false);

    // Add Scroll Pane
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Create Close Button
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dialog.dispose());

    // Add Components to Dialog
    dialog.add(scrollPane, BorderLayout.CENTER);
    dialog.add(closeButton, BorderLayout.SOUTH);

    // Position the dialog
    dialog.setLocationRelativeTo(parent);

    // Store reference to the current dialog
    currentDialog = dialog;

    // Ensure previous result is cleared
    textArea.setText(""); // Clear previous content
    textArea.append(result); // Append new result

    // Display the new dialog
    dialog.setVisible(true);
}


}
