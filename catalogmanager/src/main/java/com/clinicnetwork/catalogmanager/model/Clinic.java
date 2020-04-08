package com.clinicnetwork.catalogmanager.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Clinic implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1362457892925849111L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
	@GenericGenerator(name = "native" , strategy = "native")
	private Long id;	
	
	@Column(nullable = false)	
	private String name;
	
	@Column(nullable = false)	
	private String administrator;
	
	@Column(nullable = false)	
	private String email;
	
	@Column(nullable = false)	
	private String director;
	
	@Column(nullable = false , columnDefinition = "default 0")	
	private int capacity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_city")	
	private City city;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_clinic_type")	
	private ClinicType clinicType;

	public Clinic() 
	{
		
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAdministrator() 
	{
		return administrator;
	}

	public void setAdministrator(String administrator) 
	{
		this.administrator = administrator;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getDirector() 
	{
		return director;
	}

	public void setDirector(String director) 
	{
		this.director = director;
	}

	public int getCapacity() 
	{
		return capacity;
	}

	public void setCapacity(int capacity) 
	{
		this.capacity = capacity;
	}

	public City getCity() 
	{
		return city;
	}

	public void setCity(City city) 
	{
		this.city = city;
	}

	public ClinicType getClinicType() 
	{
		return clinicType;
	}

	public void setClinicType(ClinicType clinicType) 
	{
		this.clinicType = clinicType;
	}

	@Override
	public String toString() 
	{
		return "Clinic [id=" + id + ", name=" + name + ", administrator=" + administrator + ", email=" + email
				+ ", director=" + director + ", capacity=" + capacity + ", city=" + city + ", clinicType=" + clinicType
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((administrator == null) ? 0 : administrator.hashCode());
		result = prime * result + capacity;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((clinicType == null) ? 0 : clinicType.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Clinic other = (Clinic) obj;
		if (administrator == null) {
			if (other.administrator != null)
				return false;
		} else if (!administrator.equals(other.administrator))
			return false;
		if (capacity != other.capacity)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (clinicType == null) {
			if (other.clinicType != null)
				return false;
		} else if (!clinicType.equals(other.clinicType))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
