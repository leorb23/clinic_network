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
import com.clinicnetwork.catalogmanager.model.Zone;
import com.clinicnetwork.catalogmanager.service.api.IZoneService;

@RestController
@RequestMapping(value = "api/zones/")
public class ZoneController 
{	
	@Autowired
	private IZoneService zoneService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Zone>> getAll()
	{
		List<Zone> zones = zoneService.getAll();
		
		return new ResponseEntity<>(zones , HttpStatus.OK);		
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<Zone> get(@PathVariable Long id)
	{
		Zone zone=  zoneService.get(id);
		
		if(zone != null)
		{
			return new ResponseEntity<>(zone, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(zone, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save (@RequestBody Zone zone)
	{	
		Zone existZone = zoneService.get(zone.getId());
		if(existZone != null)
		{
			return new ResponseEntity<>("Zone already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		if (zone.getDescription() == null) 
		{
			return new ResponseEntity<>("Zone couldn't be saved: description is null!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else
		{						
			zoneService.save(zone);
			
			return new ResponseEntity<>("Zone saved successfully!", HttpStatus.OK);
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> update (@RequestBody Zone zone)
	{
		Zone optZone = zoneService.get(zone.getId());
		
		if(optZone != null)
		{
			if(zone.getDescription() != null)
			{
				zoneService.save(zone);
				return new ResponseEntity<>("Zone updated successfully!", HttpStatus.OK);
			}
			else
			{	
				return new ResponseEntity<>("Zone description is null", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<>("Zone not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id)
	{
		Zone zone = zoneService.get(id);
		
		if (zone != null) 
		{
			zoneService.delete(id);
			return new ResponseEntity<>("Zone deleted successfully!" , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Zone not found!" , HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
	
}
