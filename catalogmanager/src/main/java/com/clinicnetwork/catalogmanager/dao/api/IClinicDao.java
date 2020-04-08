package com.clinicnetwork.catalogmanager.dao.api;

import org.springframework.data.repository.CrudRepository;
import com.clinicnetwork.catalogmanager.model.Clinic;

public interface IClinicDao extends CrudRepository<Clinic, Long>{

}
