// package com.ronan.mailService;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// public class EmailSender {
//     public static void sendEmail() {
//         String pythonPath = "C:/Program Files/Python310/python.exe"; // Adjust Python path if needed
//         // String scriptPath = "C:/Rohit P/Projects/EDI_PROJECT/email-format/email-format/python/py+java/mailsender.py";
//         String scriptPath = "src/main/java/com/ronan/mailService/mailsender2.py"; // Updated script path
        
//         String recipient = "rohit.p@capgemini.com";
//         String name = "Rohit";
//         String date = "April 1, 2025";

//         try {
//             ProcessBuilder processBuilder = new ProcessBuilder(
//                 pythonPath, scriptPath, recipient, name, date
//             );
//             processBuilder.redirectErrorStream(true);

//             Process process = processBuilder.start();
//             BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

//             String line;
//             while ((line = reader.readLine()) != null) {
//                 System.out.println(line); // Print output from Python script
//             }

//             process.waitFor();
//             System.out.println("✅ Email process completed.");

//         } catch (IOException | InterruptedException e) {
//             System.out.println("❌ Error executing Python script: " + e.getMessage());
//         }
//     }

//     public static void main(String[] args) {
//         sendEmail();
//     }
// }
