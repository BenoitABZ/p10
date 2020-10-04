package com.library.LibraryBatch.scheduler;

import java.util.Date;

import javax.batch.operations.JobRestartException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BatchScheduler {

	@Autowired
	Job job;

	@Autowired
	JobLauncher jobLauncher;

	// cron = "0 0 12 * * ?"

	// fixedRate = 60000

	@Scheduled(fixedRate = 60000)
	public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Date date = new Date();
		try {
			jobLauncher.run(job, new JobParametersBuilder().addDate("date", date).toJobParameters());

			System.out.println("bonjour, vous avez du retard sur certains ouvrages empruntés sur notre réseau");
		} catch (org.springframework.batch.core.repository.JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
