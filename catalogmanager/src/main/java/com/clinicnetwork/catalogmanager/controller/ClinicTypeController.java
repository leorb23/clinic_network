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
import com.clinicnetwork.catalogmanager.model.ClinicType;
import com.clinicnetwork.catalogmanager.service.api.IClinicTypeService;

@RestController
@RequestMapping(value = "api/clinic_types/")
public class ClinicTypeController 
{
	@Autowired
	private IClinicTypeService clinicTypeService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<ClinicType>> getAll()
	{
		List<ClinicType> clinicTypes = clinicTypeService.getAll();
		
		return new ResponseEntity<>(clinicTypes , HttpStatus.OK);	
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<ClinicType> get(@PathVariable Long id)
	{
		ClinicType clinicType = clinicTypeService.get(id);
		
		if(clinicType != null)
		{
			return new ResponseEntity<>(clinicType, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(clinicType, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save (@RequestBody ClinicType clinicType)
	{
		ClinicType existClinicType = clinicTypeService.get(clinicType.getId());
		
		if(existClinicType != null)
		{
			return new ResponseEntity<>("Clinic type already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		if (clinicType.getDescription() == null) 
		{
			return new ResponseEntity<>("Clinic type couldn't be saved: description is null!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			clinicTypeService.save(clinicType);
			
			return new ResponseEntity<>("Clinic type saved successfully!", HttpStatus.OK);
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> update (@RequestBody ClinicType clinicType)
	{
		ClinicType existClinicType = clinicTypeService.get(clinicType.getId());
		
		if(existClinicType != null)
		{
			return new ResponseEntity<>("Clinic type already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		if (clinicType.getDescription() == null) 
		{
			return new ResponseEntity<>("Clinic type couldn't be saved: description is null!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			clinicTypeService.save(clinicType);
			
			return new ResponseEntity<>("Clinic type saved successfully!", HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id)
	{
		ClinicType clinicType = clinicTypeService.get(id);
		
		if (clinicType != null) 
		{
			clinicTypeService.delete(id);
			
			return new ResponseEntity<>("Clinic type updated successfully!" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Clinic type not found!" , HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
}
