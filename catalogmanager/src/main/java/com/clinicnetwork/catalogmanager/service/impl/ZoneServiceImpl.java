package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.IZoneDao;
import com.clinicnetwork.catalogmanager.model.Zone;
import com.clinicnetwork.catalogmanager.service.api.IZoneService;

@Service
public class ZoneServiceImpl extends GenericServiceImpl<Zone , Long> implements IZoneService
{	
	@Autowired
	private IZoneDao zoneDao;
	
	@Override
	public CrudRepository<Zone, Long> getDao() 
	{		
		return zoneDao;
	}
	
}
