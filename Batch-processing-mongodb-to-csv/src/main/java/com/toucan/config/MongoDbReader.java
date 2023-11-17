package com.toucan.config;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.toucan.entity.Employee;

@Configuration
@EnableBatchProcessing
public class MongoDbReader {

  @Autowired 
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Bean
  public Job readEmployee() throws Exception {
    return jobBuilderFactory.get("readEmployee").flow(step1()).end().build();
  }

  @Bean
  public Step step1() throws Exception {
    return stepBuilderFactory.get("step1").<Employee, Employee>chunk(5).reader(reader())
        .writer(writer()).build();
  }


@Bean
  public MongoItemReader<Employee> reader() {
    MongoItemReader<Employee> reader = new MongoItemReader<>();
    reader.setTemplate(mongoTemplate);
    reader.setSort(new HashMap<String, Sort.Direction>() {{
      put("_id", Direction.DESC);
    }});
  
    reader.setTargetType(Employee.class);
    reader.setQuery("{}");
    return reader;
  }
@Bean
public FlatFileItemWriter<Employee>writer(){
	FlatFileItemWriter<Employee>writer=new FlatFileItemWriter<>();
	writer.setResource(new FileSystemResource("src/main/resources/employee.csv"));
	DelimitedLineAggregator<Employee>dl=new DelimitedLineAggregator<>();
	BeanWrapperFieldExtractor<Employee>bf=new BeanWrapperFieldExtractor<>();
	bf.setNames(new String[]{"userId","FirstName","LastName","Sex","Email","Phone","DOB","JobTitle"});
	dl.setFieldExtractor(bf);
	writer.setLineAggregator(dl);
	return writer;
}
}