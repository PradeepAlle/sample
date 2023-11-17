//package com.toucan.config;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.batch.core.ItemWriteListener;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.data.MongoItemReader;
//
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
//import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import com.toucan.entity.Student;
//
//@Configuration
//@EnableBatchProcessing
//
//public class BatchConfig {
//
//	@Autowired
//	private JobBuilderFactory jb;
//	
//	@Autowired
//	private StepBuilderFactory sb;
//	@Autowired
//	private MongoTemplate template;
//	
//	@Bean
//	 MongoItemReader<Student>reader(){
//		MongoItemReader<Student> reader=new MongoItemReader<>();
//		reader.setTemplate(template);
//		reader.setTargetType(Student.class);
//		reader.setQuery("{}");
//		 Map<String, Direction> sort = new HashMap<>();
//		    sort.put("_id", Sort.Direction.ASC); // Change "id" to the appropriate field
//		    reader.setSort(sort);
//		return reader;
//	}
//	@Bean
//	public FlatFileItemWriter<Student>writer(){
//		FlatFileItemWriter<Student>writer=new FlatFileItemWriter<>();
//		writer.setResource(new FileSystemResource("src/main/resources/my.csv"));
//		DelimitedLineAggregator<Student>dl=new DelimitedLineAggregator<>();
//		BeanWrapperFieldExtractor<Student>bf=new BeanWrapperFieldExtractor<>();
//		bf.setNames(new String[]{"_id","name","gender","age"});
//		dl.setFieldExtractor(bf);
//		writer.setLineAggregator(dl);
//		return writer;
//	}
//	   @Bean
//	    public Step step(FlatFileItemWriter<Student> writer) {
//	        return sb.get("step")
//	                .<Student, Student>chunk(10)
//	                .reader(reader())
//	                .writer(writer)
//	                .build();
//	    }
//
//	    @Bean
//	    public Job job(Step step) {
//	        return jb.get("job")
//	                .incrementer(new RunIdIncrementer())
//	                .flow(step)
//	                .end()
//	                .build();
//	    }
//	}
//
