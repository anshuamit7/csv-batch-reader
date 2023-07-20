package com.spring.batch;

import com.spring.batch.config.BatchConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;


@EnableAutoConfiguration
@ContextConfiguration(classes = { BatchConfiguration.class })
@SpringBatchTest
@ExtendWith(SpringExtension.class)
public class SpringBatchConfigurationTest {

    private static final String TEST_OUTPUT = "src/test/resources/actual_output.json";

    private static final String EXPECTED_OUTPUT = "src/test/resources/expected_output.json";

    private static final String TEST_INPUT = "src/test/resources/input/test_input.csv";

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @AfterEach
    public void cleanUp() {

        jobRepositoryTestUtils.removeJobExecutions();
    }

    private JobParameters defaultJobParameters() {
        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString("file.input", TEST_INPUT);
        paramsBuilder.addString("file.output", TEST_OUTPUT);
        return paramsBuilder.toJobParameters();
    }

    @Test
    public void jobExecuted_onGivenTestOutput_thenSuccess() throws Exception {
        // given
        FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
        FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        // then
        assertEquals("importOrderJob", actualJobInstance.getJobName());
        assertEquals("COMPLETED", actualJobExitStatus.getExitCode());
        AssertFile.assertFileEquals(expectedResult, actualResult);
    }

    @Test
    public void stepExecuted_onGivenTestOutput_thenSuccess() throws Exception {

        // given
        FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
        FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("csv-order-step", defaultJobParameters());
        Collection<StepExecution> actualStepExecutions = jobExecution.getStepExecutions();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        // then
        assertEquals(1, actualStepExecutions.size());
        assertEquals("COMPLETED", actualJobExitStatus.getExitCode());
        AssertFile.assertFileEquals(expectedResult, actualResult);
    }

}