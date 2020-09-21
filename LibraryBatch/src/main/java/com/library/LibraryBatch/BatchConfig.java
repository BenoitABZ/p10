package com.library.LibraryBatch;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.library.LibraryBatch.bean.EmprunteurBean;
import com.library.LibraryBatch.proxy.EmprunteurProxy;
import com.library.LibraryBatch.writer.EmprunteurItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	/*
	 * @Autowired public DataSource dataSource;
	 */

	@Autowired
	public EmprunteurProxy emprunteurProxy;

	@Bean
	ItemReader<EmprunteurBean> read() {
		EmprunteurItemReader eir = new EmprunteurItemReader();
		ItemReader<EmprunteurBean> inputR = eir.read(emprunteurProxy);

		return inputR;
	}

	@Bean
	public EmprunteurItemProcessor processor() {
		return new EmprunteurItemProcessor();
	}

	@Bean
	public EmprunteurItemWriter write() {
		EmprunteurItemWriter writer = new EmprunteurItemWriter();
		return writer;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<EmprunteurBean, MimeMessage>chunk(10).reader(read())
				.processor(processor()).writer(write()).build();
	}

	@Bean
	public Job exportEmprunteurJob() {
		return jobBuilderFactory.get("exportEmprunteurJob").incrementer(new RunIdIncrementer()).flow(step1()).end()
				.build();
	}

}
