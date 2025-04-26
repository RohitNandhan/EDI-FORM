import sys
import win32com.client
import os
import re

def remove_duplicate_contacts(contacts):
    """Removes duplicate email addresses from the contacts string."""
    if not contacts:
        return ""

    # Split the contacts by `;`, strip spaces, and add them to a set for uniqueness
    unique_contacts = set(email.strip() for email in contacts.split(";") if email.strip())

    # Join back into a single string separated by `; `
    return "; ".join(unique_contacts)

def format_plants(plant_details):
    """Formats plant details string into a line-by-line list."""
    plant_list = plant_details.split(";")  # Double spaces used as separator
    return "<br>".join(plant_list)

def send_email(recipient, parma_id, parma_name, contacts, plant_details, supplier_unb,mail_sub,mail_body):
    try:
        # Set the correct path for the email template
        template_path = r"C:\Rohit P\PLE Cloning\edi-impl-app\mail templates\UD EDI DELFOR and DESADV in production request to test invoic.html"

        # Check if the file exists
        if not os.path.exists(template_path):
            print(f"❌ Error: Email template not found at {template_path}")
            return

        # Load email template as text
        with open(template_path, "r", encoding="utf-8") as file:
            email_body = file.read()

        # Replace "Subject:" and "Attachments:" lines with empty space
        email_body = re.sub(r"(?i)^Subject:.*?$", "", email_body, flags=re.MULTILINE)
        email_body = re.sub(r"(?i)^Attachments:.*?$", "", email_body, flags=re.MULTILINE)

        # Remove extra empty lines caused by replacements
        email_body = re.sub(r"\n\s*\n", "\n", email_body)

        # Remove duplicate emails
        contacts = remove_duplicate_contacts(contacts)
        
        formated_plants=format_plants(plant_details)

        # Replace placeholders dynamically
        email_body = email_body.replace("{parma_id}", parma_id) \
                               .replace("{parma_name}", parma_name) \
                               .replace("{contacts}", contacts) \
                               .replace("{supplier_id}", parma_id) \
                               .replace(" {supplier_name} ", parma_name) \
                               .replace("{supplier_unb}", supplier_unb) \
                               .replace("{Plant_details}", formated_plants) \
                               .replace("{mail_body}", mail_body)

        # Connect to Outlook
        outlook = win32com.client.Dispatch("Outlook.Application")
        mail = outlook.CreateItem(0)  

        # Set sender account
        sender_email = "edi.rollout@udtrucks.com"  # Change sender if needed
        for account in outlook.Session.Accounts:
            if account.SmtpAddress.lower() == sender_email.lower():
                mail._oleobj_.Invoke(*(64209, 0, 8, 0, account))
                break

        # Define email properties
        mail.To = recipient
        if contacts:
            mail.CC = contacts
        
        mail.Subject = f"{parma_id} - {parma_name} -- {mail_sub}"
        mail.HTMLBody = email_body  

        # Attach File
        # if os.path.exists(attachment_path):
        #     mail.Attachments.Add(attachment_path)
        # else:
        #     print(f"⚠ Warning: Attachment not found at {attachment_path}")

        # Display email before sending
        mail.Display()
        # print(f"✅ Email draft opened successfully for {recipient} with attachment: {attachment_path}")

    except Exception as e:
        print(f"❌ Error: {e}")

if __name__ == "__main__":
    send_email(*sys.argv[1:])
