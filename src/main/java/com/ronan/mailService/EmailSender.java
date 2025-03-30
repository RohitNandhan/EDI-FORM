package com.ronan.mailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class EmailSender {
    public static void sendEmail() {
        String pythonPath = "C:/Program Files/Python310/python.exe"; // Adjust Python path if needed
        // String scriptPath = "C:/Rohit P/Projects/EDI_PROJECT/email-format/email-format/python/py+java/mailsender.py";
        // String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailsender.py"; // Updated script path
        
        String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailsender2.py"; // Updated script path
        
        // String recipient = "rohit.p@capgemini.com";
        String name = "Rohit";
        String date = "April 1, 2025";
        String recipient = "rohit.p@capgemini.com";
        String parma_id = "51276";
        String parma_name = "RG Ray Corporation";
        String contacts = "support.edi@udtrucks.com";
        String user_id = "User123";
        String first_name = "John";
        String last_name = "Doe";
         String attachmentPath = "\"C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx\""; // Escaped path


        // try {
        //     // Fix: Use cmd.exe to execute Python script properly
        //     ProcessBuilder processBuilder = new ProcessBuilder(
        //         "cmd.exe", "/c", pythonPath, scriptPath,
        //         recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath
        //     );
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                pythonPath, scriptPath,recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath
                    );
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print output from Python script
            }

            process.waitFor();
            System.out.println("✅ Email process completed.");

        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error executing Python script: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        sendEmail();
    }
}
