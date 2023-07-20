package com.spring.batch.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BatchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private JobLauncher jobLauncher;
    @Mock
    private Job job;
    @Mock
    private JobParameters jobParameters;

    @InjectMocks
    private BatchController batchController;

    @Test
    public void shouldReturnSuccessMessageAfterJobComplete(){
        String response = String.valueOf(batchController.importCsvOrderToDBJob());
        assertThat(response).isEqualTo("CSV Batch Process Completed!!");

    }

    @Test
    public void returnSuccessMessageOnCompletion() throws Exception {
        mockMvc.perform(get("/batch/importorderjob"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("CSV Batch Process Completed!!")));
    }

}
