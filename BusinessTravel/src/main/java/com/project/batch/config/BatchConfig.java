package com.project.batch.config;

import java.util.Random;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.batch.mapper.InvitationMapper;
import com.project.batch.processor.InvitationItemProcessor;
import com.project.batch.writer.InvitationWriter;
import com.project.entities.Invitation;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    DataSource dataSource;

    private final String JOB_NAME = "emailSenderJob";
    private final String STEP_NAME = "emailSenderStep";

    Random random = new Random();
    int randomWithNextInt = random.nextInt();


    @Bean(name = "emailSenderJob")
    public Job emailSenderJob() {
        return this.jobBuilderFactory.get(JOB_NAME+randomWithNextInt)
                .start(emailSenderStep())
                .build();
    }

    @Bean
    public Step emailSenderStep() {
        return this.stepBuilderFactory
        		.get(STEP_NAME)
                .<Invitation, Invitation>chunk(100)
                .reader(activeInvitationReader())
                .processor(invitationItemProcessor())
                .writer(invitationWriter())
                .build();
    }

    @Bean
    public ItemProcessor<Invitation, Invitation> invitationItemProcessor() {
        return new InvitationItemProcessor();
    }

    @Bean
    public ItemWriter<Invitation> invitationWriter() {
        return new InvitationWriter();
    }

    @Bean
    public ItemReader<Invitation> activeInvitationReader() {
        String sql = "SELECT id_invit, titre, description, email_empl, date_created, last_updated, status, email_sent FROM invitation WHERE status=1 and email_sent=0";
        return new JdbcCursorItemReaderBuilder<Invitation>()
                .name("activeInvitationReader")
                .sql(sql)
                .dataSource(dataSource)
                .rowMapper(new InvitationMapper())
                .build();
    }
}