package com.library.LibraryBatch.processor;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.library.LibraryBatch.bean.EmprunteurBean;

public class EmprunteurLateItemProcessor implements ItemProcessor<EmprunteurBean, MimeMessage> {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public MimeMessage process(EmprunteurBean emprunteurBean) throws Exception {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		try {

			helper.setFrom("benoit.abouzeid@gmail.com");
			helper.setTo(emprunteurBean.getMail());

		} catch (NullPointerException e) {

			System.out.println("problème1");
		}

		message.setContent("bonjour, vous avez du retard sur certains ouvrages empruntés sur notre réseau",
				"text/plain");

		System.out.println(message);

		return message;
	}

}
