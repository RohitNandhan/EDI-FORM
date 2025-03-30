# import sys
# import win32com.client
# import os

# def send_email(recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachment_path):
#     try:
#         # Set the correct path for the email template
#         template_path = r"C:\Rohit P\PLE Cloning\edi-impl-app\mail templates\Additional UD WebEDI Delivery Schedule and Despatch Advice in production.html"

#         # Check if the file exists
#         if not os.path.exists(template_path):
#             print(f"❌ Error: Email template not found at {template_path}")
#             return

#         # Load email template as HTML
#         with open(template_path, "r", encoding="utf-8") as file:
#             template = file.read()

#         # Replace placeholders dynamically
#         email_body = template.replace("{parma_id}", parma_id) \
#                              .replace("{parma_name}", parma_name) \
#                              .replace("{contacts}", contacts) \
#                              .replace("{user_id}", user_id) \
#                              .replace("{first_name}", first_name) \
#                              .replace("{last_name}", last_name)

#         # Connect to Outlook
#         outlook = win32com.client.Dispatch("Outlook.Application")
#         mail = outlook.CreateItem(0)  

#         # Set sender account
#         sender_email = "rohit.p@consultant.udtrucks.com"  # Change sender if needed
#         for account in outlook.Session.Accounts:
#             if account.SmtpAddress.lower() == sender_email.lower():
#                 mail._oleobj_.Invoke(*(64209, 0, 8, 0, account))
#                 break

#         # Define email properties
#         mail.To = recipient
#         mail.Subject = f"{parma_id} - {parma_name} -- Additional UD WebEDI Delivery Schedule and Despatch Advice in production"
#         mail.HTMLBody = email_body  

#         # Attach File
#         if os.path.exists(attachment_path):
#             mail.Attachments.Add(attachment_path)
#         else:
#             print(f"⚠ Warning: Attachment not found at {attachment_path}")

#         # Send email
#         # mail.Send()
#         mail.Display()
#         print(f"✅ Email sent successfully to {recipient} with attachment: {attachment_path}")

#     except Exception as e:
#         print(f"❌ Error: {e}")

# if __name__ == "__main__":
#     send_email(*sys.argv[1:]) 
import sys
import win32com.client
import os
import re

def send_email(recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachment_path):
    try:
        # Set the correct path for the email template
        template_path = r"C:\Rohit P\PLE Cloning\edi-impl-app\mail templates\Additional UD WebEDI Delivery Schedule and Despatch Advice in production.html"

        # Check if the file exists
        if not os.path.exists(template_path):
            print(f"❌ Error: Email template not found at {template_path}")
            return

        # Load email template as HTML
        with open(template_path, "r", encoding="utf-8") as file:
            email_body = file.read()

        # Remove "Subject:" and "Attachments:" lines from the email body
        email_body = re.sub(r"(?i)^Subject:.*\n?", "", email_body)  # Case-insensitive match for "Subject:"
        email_body = re.sub(r"(?i)^Attachments:.*\n?", "", email_body)  # Case-insensitive match for "Attachments:"

        # Replace placeholders dynamically
        email_body = email_body.replace("{parma_id}", parma_id) \
                               .replace("{parma_name}", parma_name) \
                               .replace("{contacts}", contacts) \
                               .replace("{user_id}", user_id) \
                               .replace("{first_name}", first_name) \
                               .replace("{last_name}", last_name)

        # Connect to Outlook
        outlook = win32com.client.Dispatch("Outlook.Application")
        mail = outlook.CreateItem(0)  

        # Set sender account
        sender_email = "rohit.p@consultant.udtrucks.com"  # Change sender if needed
        for account in outlook.Session.Accounts:
            if account.SmtpAddress.lower() == sender_email.lower():
                mail._oleobj_.Invoke(*(64209, 0, 8, 0, account))
                break

        # Define email properties
        mail.To = recipient
        mail.Subject = f"{parma_id} - {parma_name} -- Additional UD WebEDI Delivery Schedule and Despatch Advice in production"
        mail.HTMLBody = email_body  

        # Attach File
        if os.path.exists(attachment_path):
            mail.Attachments.Add(attachment_path)
        else:
            print(f"⚠ Warning: Attachment not found at {attachment_path}")

        # Display email before sending
        mail.Display()
        print(f"✅ Email draft opened successfully for {recipient} with attachment: {attachment_path}")

    except Exception as e:
        print(f"❌ Error: {e}")

if __name__ == "__main__":
    send_email(*sys.argv[1:])

