
package springmvc.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;




public class EmailService {
    
    
    public void sendEmail(String to, String fname, String lname, String password){
    Session mailSession = createSmtpSession();
    mailSession.setDebug (true);
    String textMessage = "Hei "+fname.substring(0, 1).toUpperCase() + fname.substring(1).toLowerCase() +"! Her er ditt nye passord: "+password;
    try {
        Transport transport = mailSession.getTransport ();

        MimeMessage message = new MimeMessage (mailSession);

        message.setSubject ("Ditt nye passord til ELG ved HiST");
        message.setFrom (new InternetAddress ("ELGsystem.hist@gmail.com"));
        message.setContent (textMessage, "text/html");
        message.addRecipient (Message.RecipientType.TO, new InternetAddress (to));

        transport.connect ();
        transport.sendMessage (message, message.getRecipients (Message.RecipientType.TO));  
        System.out.println("EPOST SKAL VÆRE SENT NÅ: til "+ to+"med melding: " +textMessage );
    }
    catch (MessagingException e) {
        System.err.println("Cannot Send email");
        e.printStackTrace();
    }
    }

    private Session createSmtpSession() {
        final Properties props = new Properties();
        props.setProperty ("mail.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "" + 587);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty ("mail.transport.protocol", "smtp");
        // props.setProperty("mail.debug", "true");

        return Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication("ELGsystem.hist@gmail.com", "ELGerbest");
                }
        });
    }

}
    

