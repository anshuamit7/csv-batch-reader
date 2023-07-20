package com.spring.batch.config;

import com.spring.batch.listener.OrderJobExecutionNotificationListener;
import com.spring.batch.listener.OrderStepCompleteNotificationListener;
import com.spring.batch.model.Order;
import com.spring.batch.model.CsvOrderInput;
import com.spring.batch.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration // Informs Spring that this class contains configurations
@EnableBatchProcessing // Enables batch processing for the application
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final OrderRepository orderRepository;

    @Bean
    public FlatFileItemReader<CsvOrderInput> reader() {
        FlatFileItemReader<CsvOrderInput> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/test_file_2.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<CsvOrderInput> lineMapper() {
        DefaultLineMapper<CsvOrderInput> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","email","phone_number","parcel_weight");

        BeanWrapperFieldSetMapper<CsvOrderInput> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CsvOrderInput.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public OrderProcessor processor() {
        return new OrderProcessor();
    }

    @Bean
    public RepositoryItemWriter<Order> writer() {
        RepositoryItemWriter<Order> writer = new RepositoryItemWriter<>();
        writer.setRepository(orderRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean(name = "csv-order-step")
    public Step step1() {
        return stepBuilderFactory.get("csv-order-step").<CsvOrderInput, Order>chunk(1000)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(stepExecutionListener())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importOrderJob")
                .listener(jobExecutionListener())
                .flow(step1()).end().build();

    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

    @Bean
    public OrderJobExecutionNotificationListener stepExecutionListener() {
        return new OrderJobExecutionNotificationListener(orderRepository);
    }


    @Bean
    public OrderStepCompleteNotificationListener jobExecutionListener() {
        return new OrderStepCompleteNotificationListener();
    }
}
