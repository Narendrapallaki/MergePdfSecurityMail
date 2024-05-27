package com.mergeproject.mergecontrollor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.mergeproject.customexception.CustomeException;
import com.mergeproject.entity.MailForm;
import com.mergeproject.mergeservice.MailService;

import jakarta.mail.MessagingException;

@RestController
public class MailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/send") 
	public String mailSend(@ModelAttribute MailForm form)
			throws MessagingException, IOException, CustomeException {

		mailService.mailSender(form);
		return "successes";
	}

}
