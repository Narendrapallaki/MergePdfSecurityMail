
package com.mergeproject.mergeservice;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mergeproject.customexception.CustomeException;
import com.mergeproject.entity.MailForm;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	public TemplateEngine engine;

	
	public void mailSender(MailForm form) throws CustomeException {

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(form.getSubject());
			mimeMessageHelper.setTo(form.getToMail());
			mimeMessageHelper.setFrom("narendrapallaki2607@outlook.com");
			Context context = new Context();

			String name = "Narendra";
			context.setVariable("user", name);

			context.setVariable("content", form.getContent());
			String process = engine.process("home-template.html", context);
			mimeMessageHelper.setText(process, true);

			for (MultipartFile mf : form.getFile()) {

				ByteArrayResource iss = new ByteArrayResource(mf.getBytes());

				mimeMessageHelper.addAttachment(mf.getOriginalFilename(), iss);

			}

			javaMailSender.send(mimeMessage);

		} catch (MessagingException | IOException e) {

			throw new CustomeException("Error sending email with attachment", e);
		}
	}

}
