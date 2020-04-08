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
import com.clinicnetwork.catalogmanager.model.City;
import com.clinicnetwork.catalogmanager.service.api.ICityService;


@RestController
@RequestMapping(value = "api/cities/")
public class CityController 
{
	@Autowired
	private ICityService cityService;	
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<City>> getAll()
	{
		List<City> cities = cityService.getAll();		
		
		return new ResponseEntity<>(cities , HttpStatus.OK);		
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<City> get(@PathVariable Long id)
	{
		City city = cityService.get(id);
		
		if(city != null)
		{
			return new ResponseEntity<>(city, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(city, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save (@RequestBody City city)
	{	
		if(city.getId() != null)
		{
			City existCity = cityService.get(city.getId());
			if(existCity != null)
			{
				return new ResponseEntity<>("City already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		if (city.getDescription() == null || city.getZone() == null) 
		{
			return new ResponseEntity<>("City couldn't be saved: description is null", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			cityService.save(city);
			
			return new ResponseEntity<>("city saved successfully!", HttpStatus.OK);
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> update (@RequestBody City city)
	{		
		City optCity = cityService.get(city.getId());
		
		if(optCity != null)
		{
			City updCity = optCity;
			
			if(city.getDescription() != null)
			{
				updCity.setDescription(city.getDescription());
			}
			
			if(city.getZone() != null)
			{
				updCity.setZone(city.getZone());
			}
			
			cityService.save(updCity);
			
			return new ResponseEntity<>("City updated successfully!", HttpStatus.OK);
		}				
		else
		{						
			return new ResponseEntity<>("City not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id)
	{
		City city = cityService.get(id);
		
		if (city != null) 
		{
			cityService.delete(id);
			return new ResponseEntity<>("City deleted succesfully!" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("City not found!" , HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
}
