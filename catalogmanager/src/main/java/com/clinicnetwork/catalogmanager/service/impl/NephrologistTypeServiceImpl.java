package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.INephrologistTypeDao;
import com.clinicnetwork.catalogmanager.model.NephrologistType;
import com.clinicnetwork.catalogmanager.service.api.INephrologistTypeService;

@Service
public class NephrologistTypeServiceImpl extends GenericServiceImpl<NephrologistType , Long> implements INephrologistTypeService
{

	private INephrologistTypeDao nephrologistTypeDao;
	
	@Override
	public CrudRepository<NephrologistType, Long> getDao() 
	{		
		return nephrologistTypeDao;
	}
	
}
