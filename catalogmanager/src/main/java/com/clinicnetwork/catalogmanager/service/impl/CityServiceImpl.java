package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.ICityDao;
import com.clinicnetwork.catalogmanager.model.City;
import com.clinicnetwork.catalogmanager.service.api.ICityService;

@Service
public class CityServiceImpl extends GenericServiceImpl<City, Long> implements ICityService
{
	@Autowired
	private ICityDao cityDao;
	
	@Override
	public CrudRepository<City, Long> getDao() 
	{		
		return cityDao;
	}
}
