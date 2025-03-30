// package com.ronan.mailService;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// public class EmailSender2 {
//     public static void sendEmail() {
//         // Properly formatted paths
//         String pythonPath = "\"C:/Program Files/Python310/python.exe\""; // Corrected Python path handling
//         String scriptPath = "src/main/java/com/ronan/mailService/mailsender2.py"; // Updated script path
        
//         // Email details
//         String recipient = "rohit.p@capgemini.com";
//         String parma_id = "51276";
//         String parma_name = "RG Ray Corporation";
//         String contacts = "support.edi@udtrucks.com";
//         String user_id = "User123";
//         String first_name = "John";
//         String last_name = "Doe";
//         String attachmentPath = "\"C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx\""; // Escaped path

//         try {
//             // Fix: Use cmd.exe to execute Python script properly
//             ProcessBuilder processBuilder = new ProcessBuilder(
//                 "cmd.exe", "/c", pythonPath, scriptPath,
//                 recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath
//             );
//             processBuilder.redirectErrorStream(true);

//             // Start Python script execution
//             Process process = processBuilder.start();
//             BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

//             String line;
//             while ((line = reader.readLine()) != null) {
//                 System.out.println(line); // Print output from Python script
//             }

//             process.waitFor();
//             System.out.println("✅ Email process completed successfully.");

//         } catch (IOException | InterruptedException e) {
//             System.out.println("❌ Error executing Python script: " + e.getMessage());
//         }
//     }

//     public static void main(String[] args) {
//         sendEmail(); // Call email sender method
//     }
// }
