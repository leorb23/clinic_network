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
public class Nephrologist implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2899943659274118305L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
	@GenericGenerator(name = "native" , strategy = "native")
	private Long id;	
	
	@Column(nullable = false)	
	private String name;	
	
	@Column(nullable = false)	
	private String email;	
	
	@Column(name = "active", columnDefinition = "integer default 1" , nullable = false)	
	private boolean active = true;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nephrologist_type")	
	private NephrologistType nephrologistType;

	public Nephrologist() 
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

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}

	public NephrologistType getNephrologistType() 
	{
		return nephrologistType;
	}

	public void setNephrologistType(NephrologistType nephrologistType) 
	{
		this.nephrologistType = nephrologistType;
	}

	@Override
	public String toString() 
	{
		return "Nephrologist [id=" + id + ", name=" + name + ", email=" + email + ", active=" + active
				+ ", nephrologistType=" + nephrologistType + "]";
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nephrologistType == null) ? 0 : nephrologistType.hashCode());
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
		Nephrologist other = (Nephrologist) obj;
		if (active != other.active)
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
		if (nephrologistType == null) {
			if (other.nephrologistType != null)
				return false;
		} else if (!nephrologistType.equals(other.nephrologistType))
			return false;
		return true;
	}		
	
}
