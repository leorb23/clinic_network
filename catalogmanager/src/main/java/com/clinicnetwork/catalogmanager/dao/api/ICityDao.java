package com.clinicnetwork.catalogmanager.dao.api;

import org.springframework.data.repository.CrudRepository;
import com.clinicnetwork.catalogmanager.model.City;

public interface ICityDao extends CrudRepository<City, Long>
{

}
