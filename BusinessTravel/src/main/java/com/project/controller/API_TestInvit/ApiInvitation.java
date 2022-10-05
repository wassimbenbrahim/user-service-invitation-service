package com.project.controller.API_TestInvit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.email.EmailServiceImpl;
import com.project.entities.Invitation;
import com.project.services.InvitationService;

import javax.mail.SendFailedException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/api")
@Slf4j
public class ApiInvitation {
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    InvitationService serviceInvit ;

    @Autowired
    @Qualifier("emailSenderJob")
    private Job emailSenderJob;

    @GetMapping("/sendTestInvitation")
    public ResponseEntity<ResponseMessage> sendAllInvitations() {
        try {
            emailService.sendSimpleMessage("hello@gmail.com", "Invitaion of business travel", "Thank you for registering with us");
        } catch (SendFailedException sendFailedException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("failed to send email"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("email sent with succes"));
    }

    @GetMapping("/countInvitations")
    public ResponseEntity<InvitationsResponse> getTotalInvitation() {
        long invitationsCount = serviceInvit.countInvit();

        InvitationsResponse response = new InvitationsResponse();
        response.setMessage("success");
        HashMap<String, Long> count = new HashMap<>();
        count.put("total", invitationsCount);
        response.setResponse(count);

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sentInvitations")
    public ResponseEntity<InvitationsResponse> getEmailsSent() {
        long invitationsCount = serviceInvit.countInvitByEmailSent(true);

        InvitationsResponse response = new InvitationsResponse();
        response.setMessage("success");
        HashMap<String, Long> count = new HashMap<>();
        count.put("total", invitationsCount);
        response.setResponse(count);

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/sendInvitation")
    public ResponseEntity<ResponseMessage> sendEmails() {
        Random random = new Random();
        int randomWithNextInt = random.nextInt();

        JobParameter param = new JobParameter(String.valueOf(randomWithNextInt));
        JobParameters jobParameters = new JobParametersBuilder().addParameter("unique", param).toJobParameters();
        List<Invitation> emailNotSentInvitations = serviceInvit.findInvitByEmailSentAndStatus(false, true);

        if (emailNotSentInvitations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Nothing to send"));
        }

        try {
            final JobExecution jobExecution = jobLauncher.run(emailSenderJob, jobParameters);
                Date create = jobExecution.getStartTime();
                Date end = jobExecution.getEndTime();
                int diff = end.getSeconds() - create.getSeconds();
                log.debug("Job_Execution_Time : {}", diff+ "Second(s)");
                TimeUnit.SECONDS.sleep(diff);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success: Job_Execution_Time : " +diff+ " Second(s)"));
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | JobParametersInvalidException |InterruptedException | JobRestartException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
        }
    }
}