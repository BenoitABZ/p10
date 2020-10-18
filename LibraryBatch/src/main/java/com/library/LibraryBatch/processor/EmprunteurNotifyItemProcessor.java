package com.library.LibraryBatch.processor;

import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.library.LibraryBatch.bean.EmprunteurBean;
import com.library.LibraryBatch.bean.ReservationBean;

public class EmprunteurNotifyItemProcessor implements ItemProcessor<EmprunteurBean, MimeMessage> {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public MimeMessage process(EmprunteurBean emprunteurBean) throws Exception {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("benoit.abouzeid@gmail.com");
		helper.setTo(emprunteurBean.getMail());

		Set<ReservationBean> reservations = emprunteurBean.getReservations();

		for (ReservationBean reservation : reservations) {

			if (reservation.isNotification()) {

				message.setContent(
						"bonjour, l'ouvrage " + reservation.getOuvrage()
								+ " est maintenant disponible. Vous avez 48 heures pour proceder à son emprunt",
						"text/plain");

				break;
			}
		}
		
		System.out.println(message);

		return message;
	}

}
