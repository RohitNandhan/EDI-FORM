package com.ronan.jswing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EDIImplForm {
    public static void main(String[] args) {
        // Create Frame
        JFrame frame = new JFrame("EDI IMPL Form");
        frame.setSize(350, 250);
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

        String[] ediTypes = {"Traditional EDI", "WebEDI"};
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
                StringBuilder selectedPlants = new StringBuilder("Selected Plants: ");
                for (JCheckBox plant : plants) {
                    if (plant.isSelected()) {
                        selectedPlants.append(plant.getText()).append(", ");
                    }
                }
                String ediType = ediDropdown.getSelectedItem().toString();
                boolean isJapaneseSupplier = japSupplier.isSelected();

                // Display Selection
                JOptionPane.showMessageDialog(frame, 
                    selectedPlants + "\nEDI Type: " + ediType + "\nJapanese Supplier: " + (isJapaneseSupplier ? "Yes" : "No"),
                    "Submission Details", JOptionPane.INFORMATION_MESSAGE);
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
            }
        });

        // Set Frame Visible
        frame.setVisible(true);
    }
}
