package com.mergeproject.mergeservice;

import java.util.List;


import org.springframework.stereotype.Service;


import com.mergeproject.repository.Exceldatarepo;

@Service
public class ExcelAndPdfService {
	
	
	private final Exceldatarepo repository;
 
	
	public ExcelAndPdfService(Exceldatarepo repository) {
		
		this.repository = repository;
	}


	public List<com.mergeproject.entity.UserDetails>getAllUser()
	{
		
		Iterable<com.mergeproject.entity.UserDetails> all = repository.findAll();
		return  (List<com.mergeproject.entity.UserDetails>) all;
	}
}
