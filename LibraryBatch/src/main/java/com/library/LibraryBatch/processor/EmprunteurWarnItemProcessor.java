package com.library.LibraryBatch.processor;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.library.LibraryBatch.bean.EmprunteurBean;

public class EmprunteurWarnItemProcessor implements ItemProcessor<EmprunteurBean, MimeMessage> {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public MimeMessage process(EmprunteurBean emprunteurBean) throws Exception {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		try {

			helper.setFrom("benoit.abouzeid@gmail.com");
			helper.setTo(emprunteurBean.getMail());

			message.setContent(
					"bonjour, nous sommes dans le regret de vous informer que l'une de vos reservations a été supprimé. cette dernière ayant dépassé le délai de 48h. Pour plus d'information, consultez votre espace personnelle: " + emprunteurBean.getMail(),
					"text/plain");

		} catch (NullPointerException e) {

			System.out.println("problème3");
		}

		//System.out.println(message);

		return message;
	}

}
