/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.aravinth;

import com.commondb.Common_DB;
import com.sun.mail.smtp.SMTPTransport;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author aravinth
 */
@WebService(serviceName = "Tracking")
public class Tracking {

    /**
     * This is a sample web service operation
     */
    
    String from= "", to= "",password = "";
    static Properties properties = new Properties();
   static
   {
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.socketFactory.class",
                     "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
   }
    @WebMethod(operationName = "signin")
    public String signin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
         try {
           Common_DB cd=new Common_DB();
            ResultSet rs=Common_DB.LoginCheck("chipersms", "sms","username","password", username, password);
            if(rs.next()) {
            return "success";
            }
            else {
                return "username or password is invalid";
            }
        } catch (Exception ex) {
            Logger.getLogger(Tracking.class.getName()).log(Level.SEVERE, null, ex);
            return "server temporarily not available";
        }       
    }    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "signup")
    public String signup(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email) {
       try {
           Common_DB cd=new Common_DB();
            int rs=Common_DB.InsertTable("chipersms", "INSERT INTO sms(username,password,email) VALUES('"+username+"','"+password+"','"+email+"')");
            if(rs>0) {
            return "success";
            }
            else {
                return "username is already exists";
            }
        } catch (Exception ex) {
            Logger.getLogger(Tracking.class.getName()).log(Level.SEVERE, null, ex);
            return "server temporarily not available";
        }
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SendMail")
    public String SendMail(@WebParam(name = "mail_id") String mail_id, @WebParam(name = "key_value") String key_value) {
        try {
            System.out.println("mail : "+mail_id+"..key   : "+key_value);
		 from="datamaincent@gmail.com";
        password="java@987";
         Session session = Session.getInstance(properties,  
            new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
            }});
         
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO, 
            InternetAddress.parse(mail_id));
         message.setSubject("Key for Decryption");
         message.setText("Please enter this to Decrypt Message : "+key_value);
   
      //      // Send message
      Transport.send(message);
    
     System.out.println("Email sent successfully");
 	
        }
        catch(Exception ex)
        {
            System.out.println("Exception : "+ex.getMessage());
            return ex.getMessage();
        }
        return "success";
    }

   
}