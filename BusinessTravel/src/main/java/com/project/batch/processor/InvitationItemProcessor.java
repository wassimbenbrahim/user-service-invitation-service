package com.project.batch.processor;

import com.project.email.EmailServiceImpl;
import com.project.entities.Invitation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.SendFailedException;

@Slf4j
public class InvitationItemProcessor implements ItemProcessor<Invitation, Invitation> {
	
    @Autowired
    EmailServiceImpl emailService;
    
    

    @Override
    public Invitation process(Invitation invitation) throws Exception {
        log.debug(String.valueOf(invitation.getEmailEmpl()));

        try {
        	 String title="Invitaion of business travel";
        	 String description="<h4>To accept the invitation click on this link:</h4> "+" <button>http://localhost:8080/api/v1/invitation/retrieve-invitation/"+invitation.getIdInvit()+"</button> <img src='https://media.discordapp.net/attachments/776190120927821846/950523223693484032/1646486323389.jpeg'>";
        	 
            emailService.sendSimpleMessage(invitation.getEmailEmpl(), title, description);
            invitation.setEmailSent(true);
        } catch (SendFailedException sendFailedException) {
            log.debug("error: {}", sendFailedException.getMessage());
        }
        return invitation;
    }
}