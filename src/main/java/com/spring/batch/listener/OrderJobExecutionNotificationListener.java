package com.spring.batch.listener;

import com.spring.batch.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Slf4j
@RequiredArgsConstructor
public class OrderJobExecutionNotificationListener extends JobExecutionListenerSupport {

    private final OrderRepository orderRepository;

    @Override
    public void beforeJob(JobExecution jobExecution){
        log.info("UserJobExecutionNotificationListener | beforeJob | Executing job id : " +jobExecution.getJobId());
        super.beforeJob(jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution){

        log.info("UserJobExecutionNotificationListener | afterJob | Executing job id : " +jobExecution.getJobId());

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job Completed");
        }
        orderRepository.findAll()
                .forEach(order -> log.info("Found (" + order + ">) in the database.") );
    }
}
