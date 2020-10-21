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
	Job jobLate;

	@Autowired
	Job jobNotify;

	@Autowired
	Job jobWarn;

	@Autowired
	JobLauncher jobLauncher;

	// cron = "0 0 12 * * ?"

	// fixedRate = 60000

	@Scheduled(fixedRate = 60000)
	public void scheduleLate() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Date date = new Date();
		try {
			jobLauncher.run(jobLate, new JobParametersBuilder().addDate("date", date).toJobParameters());

			System.out.println("Bonjour, vous avez du retard sur certains ouvrages empruntés sur notre réseau JOBLATE");

		} catch (org.springframework.batch.core.repository.JobRestartException e) {

			e.printStackTrace();
		}

	}
	
	//cron="0 0 9-17 * * MON-FRI"
	
	 //fixedRate = 70000
	
	@Scheduled(cron="0 0 0 * * MON-FRI" )  
	public void scheduleNotify() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Date date = new Date();
		try {
			jobLauncher.run(jobNotify, new JobParametersBuilder().addDate("date", date).toJobParameters());

			System.out.println("Bonjour, un ouvrage que vous avez réservé est disponible JOBNOTIFY");

		} catch (org.springframework.batch.core.repository.JobRestartException e) {

			e.printStackTrace();
		}

	}
	
	//cron="0 0 1 * * MON-FRI"

    //fixedRate = 65000
	
	@Scheduled(cron="1 0 0 * * MON-FRI")  
	public void scheduleWarn() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Date date = new Date();
		try {
			jobLauncher.run(jobWarn, new JobParametersBuilder().addDate("date", date).toJobParameters());

			System.out.println("Bonjour, le délai de 48 heures est dépassé, votre réservation a été supprimé JOBWARN");

		} catch (org.springframework.batch.core.repository.JobRestartException e) {

			e.printStackTrace();
		}

	}

}
