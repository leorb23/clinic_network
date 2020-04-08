package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.INephrologistDao;
import com.clinicnetwork.catalogmanager.model.Nephrologist;
import com.clinicnetwork.catalogmanager.service.api.INephrologistService;

@Service
public class NephrologistServiceImpl extends GenericServiceImpl<Nephrologist , Long> implements INephrologistService
{
	@Autowired
	private INephrologistDao nephrologistDao;

	@Override
	public CrudRepository<Nephrologist, Long> getDao() 
	{		
		return nephrologistDao;
	}
}
