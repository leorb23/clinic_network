package com.clinicnetwork.catalogmanager.dao.api;

import org.springframework.data.repository.CrudRepository;
import com.clinicnetwork.catalogmanager.model.Nephrologist;

public interface INephrologistDao extends CrudRepository<Nephrologist, Long>
{

}
