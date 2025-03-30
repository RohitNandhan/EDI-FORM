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
import com.ronan.mailService.EmailSender;
import com.ronan.repositories.JsonReader;

public class EDIImpl_model1 {

    private static List<PARMA> parmaList = new ArrayList<>();
    private static boolean isJapanese;
    private static EDITYPE editype;
    private static String supplierId;
    private static String supplierName;
    private static String supplierEmail;
    private static List<PlantDTO> plantInfoList = new ArrayList<>();

    public static void main(String[] args) {
        createForm();
    }

    public static void createForm() {
        // Create Frame
        JFrame frame = new JFrame("EDI IMPL Form");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Apply Gradient Background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.MAGENTA);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        frame.setContentPane(panel);

        // Supplier ID
        JLabel supplierIdLabel = new JLabel("Supplier ID:");
        supplierIdLabel.setBounds(20, 10, 100, 20);
        panel.add(supplierIdLabel);

        JTextField supplierIdField = new JTextField();
        supplierIdField.setBounds(130, 10, 150, 20);
        panel.add(supplierIdField);

        // Supplier Name
        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameLabel.setBounds(20, 40, 100, 20);
        panel.add(supplierNameLabel);

        JTextField supplierNameField = new JTextField();
        supplierNameField.setBounds(130, 40, 150, 20);
        panel.add(supplierNameField);

        // Supplier Email
        JLabel supplierEmailLabel = new JLabel("Supplier Email:");
        supplierEmailLabel.setBounds(20, 70, 100, 20);
        panel.add(supplierEmailLabel);

        JTextField supplierEmailField = new JTextField();
        supplierEmailField.setBounds(130, 70, 200, 20);
        panel.add(supplierEmailField);

        // Select Plants Label
        JLabel plantLabel = new JLabel("Select Plants:");
        plantLabel.setBounds(20, 100, 100, 20);
        panel.add(plantLabel);

        JCheckBox[] plants = {
            new JCheckBox("2920"), new JCheckBox("2921"),
            new JCheckBox("2922"), new JCheckBox("2924"),
            new JCheckBox("7876"), new JCheckBox("8374"),
            new JCheckBox("8417"), new JCheckBox("8431")
        };

        int x = 20, y = 120;
        for (int i = 0; i < plants.length; i++) {
            plants[i].setBounds(x, y, 60, 20);
            panel.add(plants[i]);
            x += 60;
            if (i == 3) {
                x = 20;
                y += 20;
            }
        }

        JCheckBox japSupplier = new JCheckBox("Japanese Supplier");
        japSupplier.setBounds(20, 170, 150, 20);
        panel.add(japSupplier);

        JLabel ediLabel = new JLabel("EDI Type:");
        ediLabel.setBounds(20, 200, 100, 20);
        panel.add(ediLabel);

        String[] ediTypes = {"TraditionalEDI", "WebEDI"};
        JComboBox<String> ediDropdown = new JComboBox<>(ediTypes);
        ediDropdown.setBounds(100, 200, 120, 20);
        panel.add(ediDropdown);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(80, 250, 100, 30);
        panel.add(submitButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(200, 250, 100, 30);
        panel.add(clearButton);

        JButton mailButton = new JButton("Send Mail");
        mailButton.setBounds(320, 250, 120, 30);
        panel.add(mailButton);

        frame.setVisible(true);
    }
}
