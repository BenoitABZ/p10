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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BatchScheduler {

	@Autowired
	@Qualifier("lateJob")
	Job jobLate;

	@Autowired
	@Qualifier("notifyJob")
	Job jobNotify;

	@Autowired
	@Qualifier("warnJob")
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

			System.out.println("Bonjour, vous avez du retard sur certains ouvrages empruntés sur notre réseau");

		} catch (org.springframework.batch.core.repository.JobRestartException e) {

			e.printStackTrace();
		}

	}

	@Scheduled(fixedRate = 70000)
	public void scheduleNotify() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Date date = new Date();
		try {
			jobLauncher.run(jobNotify, new JobParametersBuilder().addDate("date", date).toJobParameters());

			System.out.println("Bonjour, un ouvrage que vous avez réservé est disponible");

		} catch (org.springframework.batch.core.repository.JobRestartException e) {

			e.printStackTrace();
		}

	}

	@Scheduled(fixedRate = 65000)
	public void scheduleWarn() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		Date date = new Date();
		try {
			jobLauncher.run(jobWarn, new JobParametersBuilder().addDate("date", date).toJobParameters());

			System.out.println("Bonjour, le délai de 48 heures est dépassé, votre réservation a été supprimé");

		} catch (org.springframework.batch.core.repository.JobRestartException e) {

			e.printStackTrace();
		}

	}

}
