// package com.ronan.mailService;

// import org.junit.jupiter.api.Test;

// public class EmailSenderTest {
   
//     @Test
//     void testSendEmail() {
//         String pythonPath = "C:/Program Files/Python310/python.exe"; 
//         String scriptPath = "C:/Rohit P/PLE Cloning/edi-impl-app/EDI-FORM/src/main/java/com/ronan/mailService/mailsender2.py";
        
//         String recipient = "test@example.com";
//         String parma_id = "51276";
//         String parma_name = "Test Corporation";
//         String contacts = "support@example.com";
//         String user_id = "TestUser";
//         String first_name = "John";
//         String last_name = "Doe";
//         String attachmentPath = "C:/Rohit P/PLE Cloning/Password Reset in Saviynt.docx";

//         try {
//             ProcessBuilder processBuilder = new ProcessBuilder(
//                 pythonPath, scriptPath, recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachmentPath
//             );
//             processBuilder.redirectErrorStream(true);

//             Process process = processBuilder.start();
//            // BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
//             StringBuilder output = new StringBuilder();
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 output.append(line).append("\n");
//             }
            
//             int exitCode = process.waitFor();
            
//             try {
//                 // Check if process executed successfully
//                 assertEquals(0, exitCode, "Python script did not exit normally");
//                 assertTrue(output.toString().contains("✅ Email draft opened successfully"), "Email draft message not found");
//             } catch (AssertionFailedError e) {
//                 System.err.println("⚠ Test Assertion Failed: " + e.getMessage());
//                 e.printStackTrace(); // Print stack trace for debugging
//                 fail("Test failed due to assertion error: " + e.getMessage()); // Keep test failure but with a message
//             }

//         } catch (IOException | InterruptedException e) {
//             fail("Exception occurred: " + e.getMessage());
//         }
//     }
// }