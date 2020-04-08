package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.IClinicTypeDao;
import com.clinicnetwork.catalogmanager.model.ClinicType;
import com.clinicnetwork.catalogmanager.service.api.IClinicTypeService;

@Service
public class ClinicTypeServiceImpl extends GenericServiceImpl<ClinicType , Long> implements IClinicTypeService
{
	@Autowired
	private IClinicTypeDao clinicTypeDao;
	
	@Override
	public CrudRepository<ClinicType, Long> getDao() 
	{
		return clinicTypeDao;
	}
}
