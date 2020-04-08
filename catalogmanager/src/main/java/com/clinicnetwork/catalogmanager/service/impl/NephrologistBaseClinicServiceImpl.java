package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.INephrologistBaseClinicDao;
import com.clinicnetwork.catalogmanager.model.NephrologistBaseClinic;
import com.clinicnetwork.catalogmanager.service.api.INephrologistBaseClinicService;

@Service
public class NephrologistBaseClinicServiceImpl extends GenericServiceImpl<NephrologistBaseClinic , Long> implements INephrologistBaseClinicService
{
	@Autowired
	private INephrologistBaseClinicDao nephrologistBaseClinicDao;
	
	@Override
	public CrudRepository<NephrologistBaseClinic, Long> getDao() 
	{		
		return nephrologistBaseClinicDao;
	}

}
