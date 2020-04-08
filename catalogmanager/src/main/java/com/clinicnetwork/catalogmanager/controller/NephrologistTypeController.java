package com.clinicnetwork.catalogmanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinicnetwork.catalogmanager.model.NephrologistType;
import com.clinicnetwork.catalogmanager.service.api.INephrologistTypeService;

@RestController
@RequestMapping(value = "api/nephrologist_types/")
public class NephrologistTypeController 
{
	@Autowired
	private INephrologistTypeService nephrologistTypeService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<NephrologistType>> getAll()
	{
		List<NephrologistType> nephrologistTypes = nephrologistTypeService.getAll();
		
		return new ResponseEntity<>(nephrologistTypes , HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<NephrologistType> get(@PathVariable Long id)
	{
		NephrologistType nephrologistType = nephrologistTypeService.get(id);
		
		if(nephrologistType != null)
		{
			return new ResponseEntity<>(nephrologistType, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(nephrologistType, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save (@RequestBody NephrologistType nephrologistType)
	{
		NephrologistType existNephrologistType = nephrologistTypeService.get(nephrologistType.getId());
		
		if(existNephrologistType != null)
		{
			return new ResponseEntity<>("Nephrologist type type already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		if (nephrologistType.getDescription() == null) 
		{
			return new ResponseEntity<>("Nephrologist type type couldn't be saved: description is null!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			nephrologistTypeService.save(nephrologistType);
			
			return new ResponseEntity<>("Nephrologist type type saved successfully!", HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<String> update (@RequestBody NephrologistType nephrologistType)
	{
		NephrologistType existNephrologistType = nephrologistTypeService.get(nephrologistType.getId());
		
		if(existNephrologistType != null)
		{
			return new ResponseEntity<>("Nephrologist type type already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		if (nephrologistType.getDescription() == null) 
		{
			return new ResponseEntity<>("Nephrologist type type couldn't be saved: description is null!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			nephrologistTypeService.save(nephrologistType);
			
			return new ResponseEntity<>("Nephrologist type type saved successfully!", HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id)
	{
		NephrologistType nephrologistType = nephrologistTypeService.get(id);
		
		if (nephrologistType != null) 
		{
			nephrologistTypeService.delete(id);
			
			return new ResponseEntity<>("Nephrologist type updated successfully!" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Nephrologist type not found!" , HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
}
