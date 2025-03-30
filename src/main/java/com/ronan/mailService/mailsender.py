import win32com.client
import os
import sys

def send_email(recipient, parma_id, parma_name, contacts, user_id, first_name, last_name, attachment_path):
 
    try:
        # Get the Desktop path dynamically
        # desktop_path = os.path.join(os.path.expanduser("~"), "Desktop", "prod_mail.html")
        desktop_path = r"C:\Rohit P\PLE Cloning\edi-impl-app\mail templates\Additional UD WebEDI Delivery Schedule and Despatch Advice in production.html"


        # Load email template as HTML
        with open(desktop_path, "r", encoding="utf-8") as file:
            template = file.read()

        # Replace placeholders with actual values
        email_body = template.replace("{parma_name}", parma_name).replace("{parma_id}", parma_id)

        # Connect to Outlook
        outlook = win32com.client.Dispatch("Outlook.Application")
        mail = outlook.CreateItem(0)  # 0 = Mail item
        
        # Set sender account
        sender_email = "rohit.p@consultant.udtrucks.com"
        for account in outlook.Session.Accounts:
            if account.SmtpAddress.lower() == sender_email.lower():
                mail._oleobj_.Invoke(*(64209, 0, 8, 0, account))
                break

        # Define email properties
        mail.To = recipient
        mail.Subject = "Meeting Reminder"
        mail.HTMLBody = email_body  # ✅ This ensures the structure is maintained

        # Send email
        mail.Send()
        print(f"✅ Email sent to {recipient}")

    except Exception as e:
        print(f"❌ Error: {e}")

# Example usage

# send_email("rohit.p@capgemini.com", "Rohit", "April 1, 2025")
# send_email()
if __name__ == "__main__":
    send_email(*sys.argv[1:]) 

