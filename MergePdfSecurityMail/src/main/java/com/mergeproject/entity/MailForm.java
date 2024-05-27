package com.mergeproject.entity;



import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailForm {
	
	private String subject;
	private String toMail;
	private String content;
	
	private MultipartFile[] file;

}
