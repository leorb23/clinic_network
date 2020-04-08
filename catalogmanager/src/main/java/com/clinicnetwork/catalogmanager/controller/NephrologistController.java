package com.clinicnetwork.catalogmanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinicnetwork.catalogmanager.model.Nephrologist;
import com.clinicnetwork.catalogmanager.service.api.INephrologistService;

@RestController
@RequestMapping(value = "/api/nephrologists/")
public class NephrologistController 
{
	@Autowired
	private INephrologistService nephrologistService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Nephrologist>> getAll()
	{
		List<Nephrologist> nephrologists = nephrologistService.getAll();
		
		return new ResponseEntity<>(nephrologists , HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<Nephrologist> get(@PathVariable Long id)
	{
		Nephrologist nephrologist = nephrologistService.get(id);
		
		if(nephrologist != null)
		{
			return new ResponseEntity<>(nephrologist, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(nephrologist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save (@RequestBody Nephrologist nephrologist)
	{
		Nephrologist existNephrologist = nephrologistService.save(nephrologist);
		
		if(existNephrologist != null)
		{
			return new ResponseEntity<>("Nephrologist already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if (nephrologist.getName() == null || nephrologist.getNephrologistType() == null || nephrologist.getEmail() == null) 
		{
			return new ResponseEntity<>("Nephrologist couldn't be saved: there are null values", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			nephrologistService.save(nephrologist);
			
			return new ResponseEntity<>("Clinic saved successfully!", HttpStatus.OK);
		}		
		
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> update (@RequestBody Nephrologist nephrologist)
	{
		Nephrologist optNephrologist = nephrologistService.get(nephrologist.getId());
		
		if(optNephrologist != null)
		{
			Nephrologist updNephrologist = optNephrologist;
			
			if(nephrologist.getEmail() != null)
			{
				updNephrologist.setEmail(nephrologist.getEmail());
			}
			
			if(nephrologist.getName() != null)
			{
				updNephrologist.setName(nephrologist.getName());
			}			
			
			if(nephrologist.getNephrologistType() != null)
			{
				updNephrologist.setNephrologistType(nephrologist.getNephrologistType());
			}
			
			nephrologistService.save(updNephrologist);
			
			return new ResponseEntity<>("Nephrologist updated successfully!", HttpStatus.OK);			
		}
		else
		{
			return new ResponseEntity<>("Nephrologist not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id)
	{
		Nephrologist nephrologist = nephrologistService.get(id);
		
		if (nephrologist != null) 
		{
			nephrologistService.delete(id);
			
			return new ResponseEntity<>("Nephrologist deleted successfully!" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Nephrologist not found" , HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
