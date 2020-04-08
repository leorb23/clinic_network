package com.clinicnetwork.catalogmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ClinicType implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3422164405620429979L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
	@GenericGenerator(name = "native" , strategy = "native")
	private Long id;
	
	@Column(nullable = false)	
	private String description;	
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}	
	
	public ClinicType() {
		
	}

	@Override
	public String toString() 
	{
		return "ClinicType [id=" + id + ", description=" + description + "]";
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicType other = (ClinicType) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

			
}
