package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.IClinicDao;
import com.clinicnetwork.catalogmanager.model.Clinic;
import com.clinicnetwork.catalogmanager.service.api.IClinicService;

@Service
public class ClinicServiceImpl extends GenericServiceImpl<Clinic , Long> implements IClinicService
{
	@Autowired
	private IClinicDao clinicServiceDao;
	
	@Override
	public CrudRepository<Clinic, Long> getDao() 
	{		
		return clinicServiceDao;
	}
}
