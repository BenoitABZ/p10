package com.library.LibraryBatch;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class EmprunteurItemWriter implements ItemWriter<MimeMessage> {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void write(List<? extends MimeMessage> messages) throws Exception {

		messages.stream().forEach((message) -> mailSender.send(message));

	}

}
