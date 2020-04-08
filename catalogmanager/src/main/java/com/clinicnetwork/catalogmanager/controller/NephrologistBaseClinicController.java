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
import com.clinicnetwork.catalogmanager.model.NephrologistBaseClinic;
import com.clinicnetwork.catalogmanager.service.api.INephrologistBaseClinicService;

@RestController
@RequestMapping(value = "api/nephrologist_base_clinics/")
public class NephrologistBaseClinicController 
{
	@Autowired
	private INephrologistBaseClinicService nephrologistBaseClinicService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<NephrologistBaseClinic>> getAll()
	{
		List<NephrologistBaseClinic> nephrologistBaseClinics = nephrologistBaseClinicService.getAll();
		
		return new ResponseEntity<>(nephrologistBaseClinics , HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<NephrologistBaseClinic> get(@PathVariable Long id)
	{
		NephrologistBaseClinic nephrologistBaseClinic = nephrologistBaseClinicService.get(id);
		
		if(nephrologistBaseClinic != null)
		{
			return new ResponseEntity<>(nephrologistBaseClinic, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(nephrologistBaseClinic, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save (@RequestBody NephrologistBaseClinic nephrologistBaseClinic)
	{
		NephrologistBaseClinic obj = nephrologistBaseClinicService.get(nephrologistBaseClinic.getId());
		
		if(obj != null)
		{
			return new ResponseEntity<>("Nephrologist Base clinic!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if (nephrologistBaseClinic.getNephrologist() == null || nephrologistBaseClinic.getClinic() == null ) 
		{
			return new ResponseEntity<>("Nephrologist Base clinic couldn't be saved: there are null values", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			nephrologistBaseClinicService.save(nephrologistBaseClinic);
			
			return new ResponseEntity<>("Nephrologist Base clinic saved successfully!", HttpStatus.OK);
		}		
		
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> update (@RequestBody NephrologistBaseClinic nephrologistBaseClinic)
	{
		NephrologistBaseClinic optNephrologistBaseClinicService = nephrologistBaseClinicService.get(nephrologistBaseClinic.getId());
		
		if(optNephrologistBaseClinicService != null)
		{
			NephrologistBaseClinic updNephrologistBaseClinicService = optNephrologistBaseClinicService;
			if(nephrologistBaseClinic.getClinic() != null)
			{
				updNephrologistBaseClinicService.setClinic(nephrologistBaseClinic.getClinic());
			}
			
			if(nephrologistBaseClinic.getNephrologist() != null)
			{
				updNephrologistBaseClinicService.setNephrologist(nephrologistBaseClinic.getNephrologist());
			}			
			
			nephrologistBaseClinicService.save(updNephrologistBaseClinicService);
			
			return new ResponseEntity<>("Nephrologist Base clinic updated successfully!", HttpStatus.OK);			
		}
		else
		{
			return new ResponseEntity<>("Nephrologist Base clinic not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id)
	{
		NephrologistBaseClinic nephrologistBaseClinic = nephrologistBaseClinicService.get(id);
		
		if (nephrologistBaseClinic != null) 
		{
			nephrologistBaseClinicService.delete(id);
			
			return new ResponseEntity<>("Nephrologist Base clinic deleted Successfully!" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Nephrologist Base clinic not found!" , HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
