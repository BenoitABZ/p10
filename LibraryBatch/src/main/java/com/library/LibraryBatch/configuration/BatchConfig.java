package com.library.LibraryBatch.configuration;

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
import com.library.LibraryBatch.processor.EmprunteurLateItemProcessor;
import com.library.LibraryBatch.processor.EmprunteurNotifyItemProcessor;
import com.library.LibraryBatch.processor.EmprunteurWarnItemProcessor;
import com.library.LibraryBatch.proxy.EmprunteurProxy;
import com.library.LibraryBatch.reader.EmprunteurLateItemReader;
import com.library.LibraryBatch.reader.EmprunteurNotifyItemReader;
import com.library.LibraryBatch.reader.EmprunteurWarnItemReader;
import com.library.LibraryBatch.writer.EmprunteurItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public EmprunteurProxy emprunteurProxy;

	@Bean
	ItemReader<EmprunteurBean> readLate() {
		EmprunteurLateItemReader eir = new EmprunteurLateItemReader();
		ItemReader<EmprunteurBean> inputR = eir.read(emprunteurProxy);

		return inputR;
	}
	
	@Bean
	ItemReader<EmprunteurBean> readNotify() {
		EmprunteurNotifyItemReader eir = new EmprunteurNotifyItemReader();
		ItemReader<EmprunteurBean> inputR = eir.read(emprunteurProxy);

		return inputR;
	}
	
	@Bean
	ItemReader<EmprunteurBean> readWarn() {
		EmprunteurWarnItemReader eir = new EmprunteurWarnItemReader();
		ItemReader<EmprunteurBean> inputR = eir.read(emprunteurProxy);

		return inputR;
	}

	@Bean
	public EmprunteurLateItemProcessor processorLate() {
		return new EmprunteurLateItemProcessor();
	}
	
	@Bean
	public EmprunteurNotifyItemProcessor processorNotify() {
		return new EmprunteurNotifyItemProcessor();
	}
	
	@Bean
	public EmprunteurWarnItemProcessor processorWarn() {
		return new EmprunteurWarnItemProcessor();
	}


	@Bean
	public EmprunteurItemWriter write() {
		EmprunteurItemWriter writer = new EmprunteurItemWriter();
		return writer;
	}

	@Bean
	public Step getLateStep() {
		return stepBuilderFactory.get("getLateStep").<EmprunteurBean, MimeMessage>chunk(10).reader(readLate())
				.processor(processorLate()).writer(write()).build();
	}
	
	@Bean
	public Step getNotifyStep() {
		return stepBuilderFactory.get("getNotifyStep").<EmprunteurBean, MimeMessage>chunk(10).reader(readNotify())
				.processor(processorNotify()).writer(write()).build();
	}
	
	@Bean
	public Step getWarnStep() {
		return stepBuilderFactory.get("getWarnStep").<EmprunteurBean, MimeMessage>chunk(10).reader(readWarn())
				.processor(processorWarn()).writer(write()).build();
	}

	@Bean("lateJob")
	public Job getLateJob() {
		return jobBuilderFactory.get("getLateJob").incrementer(new RunIdIncrementer()).flow(getLateStep()).end()
				.build();
	}
	
	@Bean("notifyJob")
	public Job getNotifyJob() {
		return jobBuilderFactory.get("getNotifyJob").incrementer(new RunIdIncrementer()).flow(getNotifyStep()).end()
				.build();
	}
	
	@Bean("warnJob")
	public Job getWarnJob() {
		return jobBuilderFactory.get("getWarnJob").incrementer(new RunIdIncrementer()).flow(getWarnStep()).end()
				.build();
	}

}
