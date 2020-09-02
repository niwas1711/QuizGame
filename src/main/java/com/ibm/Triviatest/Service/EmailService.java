package com.ibm.Triviatest.Service;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService
{

	
	 @Autowired
	    public JavaMailSender emailSender;
	    
	 
	    
	
	    
	    public void sendSimpleMessage(String to,String from, String subject, String text)
	    
	    {
	       
	    	try {
	            SimpleMailMessage message = new SimpleMailMessage();
	          message.setFrom(from);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setText(text);
          
	            emailSender.send(message);
	        } 
	        catch (MailException exception) {
	            exception.printStackTrace();
	        }
	    }
	



	  
   

	    
}
