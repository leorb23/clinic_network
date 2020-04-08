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
import com.clinicnetwork.catalogmanager.model.Clinic;
import com.clinicnetwork.catalogmanager.service.api.IClinicService;

@RestController
@RequestMapping(value = "api/clinics/")
public class ClinicController 
{
	@Autowired
	private IClinicService clinicService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Clinic>> getAll()
	{
		List<Clinic> clinics = clinicService.getAll();		
		
		return new ResponseEntity<>(clinics , HttpStatus.OK);		
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<Clinic> get(@PathVariable Long id)
	{
		Clinic clinic =  clinicService.get(id);
		
		if(clinic != null)
		{
			return new ResponseEntity<>(clinic, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(clinic, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save (@RequestBody Clinic clinic)
	{
		Clinic existClinic = clinicService.get(clinic.getId());
		
		if(existClinic != null)
		{
			return new ResponseEntity<>("Clinic already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if (clinic.getAdministrator() == null || clinic.getName() == null || clinic.getCity() == null || clinic.getDirector() == null || clinic.getEmail() == null ||clinic.getClinicType() == null) 
		{
			return new ResponseEntity<>("Clinic couldn't be saved: there are null values", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			clinicService.save(clinic);
			
			return new ResponseEntity<>("Clinic saved successfully!", HttpStatus.OK);
		}		
		
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> update (@RequestBody Clinic clinic)
	{
		Clinic optClinic = clinicService.get(clinic.getId());
		
		if(optClinic != null)
		{
			Clinic updClinic = optClinic;
			if(clinic.getEmail() != null)
			{
				updClinic.setEmail(clinic.getEmail());
			}
			
			if(clinic.getName() != null)
			{
				updClinic.setName(clinic.getName());
			}
			
			if(clinic.getAdministrator() != null)
			{
				updClinic.setAdministrator(clinic.getAdministrator());
			}
			
			if(clinic.getDirector() != null)
			{
				updClinic.setDirector(clinic.getDirector());
			}
			
			if(clinic.getCity() != null)
			{
				updClinic.setCity(clinic.getCity());
			}
			
			if(clinic.getClinicType() != null)
			{
				updClinic.setClinicType(clinic.getClinicType());
			}
			
			clinicService.save(updClinic);
			
			return new ResponseEntity<>("Clinic updated successfully!", HttpStatus.OK);			
		}
		else
		{
			return new ResponseEntity<>("Clinic not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id)
	{
		Clinic clinic = clinicService.get(id);
		
		if (clinic != null) 
		{
			clinicService.delete(id);
			
			return new ResponseEntity<>("Clinic deleted successfully!" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Clinic not found" , HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
