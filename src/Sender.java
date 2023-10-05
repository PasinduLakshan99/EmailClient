import com.sun.mail.util.MailConnectException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class Sender {
    public static void sendMail(Mail mail, Boolean birthdayWish) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        // prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        String senderAddress = "<your email>";
        String password = "<your app passwd>";
        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderAddress, password);
            }
        });
        if (!birthdayWish) {
            System.out.println("Sending email......");
            Message message = prepareMessage(session, senderAddress, mail);
            try {
                Transport.send(message);
                SentBox.serialize(mail);
                System.out.println("Email Succesfuly sent to " + mail.recipientName);
            }
            catch (SendFailedException ex){
                System.out.println("Invalid Email Adress!");
            }
            catch (MailConnectException ex){
                System.out.println("!!!!!!Connection Error!!!!!!");
                System.out.println("Please check your connection");
            }
            catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
        if (birthdayWish) {
            System.out.println("Sending Birthday wish to " + mail.recipientName + "......");
            Message message = prepareMessage(session, senderAddress, mail);
            Transport.send(message);
            SentBox.serialize(mail);
            System.out.println("Birthday Wish Succesfuly sent!");
        }
    }
    private static Message prepareMessage(Session session, String myEmail,Mail mail) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail.recipientAddress));
            message.setSubject(mail.subject);
            message.setText(mail.body);
            return message;
        } catch (Exception e) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
