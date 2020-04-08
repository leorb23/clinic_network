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
public class NephrologistBaseClinic implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 459996122529574858L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
	@GenericGenerator(name = "native" , strategy = "native")
	private Long id;	
	
	@Column(nullable = false, columnDefinition = "default 0.0")	
	private double salary;
	
	@Column(nullable = false , columnDefinition = "default 0")	
	private int dedicationHours;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_clinic")	
	private Clinic clinic;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nephrologist")	
	private Nephrologist nephrologist;	

	public NephrologistBaseClinic() 
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

	public double getSalary() 
	{
		return salary;
	}

	public void setSalary(double salary) 
	{
		this.salary = salary;
	}

	public int getDedicationHours() 
	{
		return dedicationHours;
	}

	public void setDedicationHours(int dedicationHours) 
	{
		this.dedicationHours = dedicationHours;
	}

	public Clinic getClinic() 
	{
		return clinic;
	}
	
	public void setClinic(Clinic clinic) 
	{
		this.clinic = clinic;
	}

	public Nephrologist getNephrologist() 
	{
		return nephrologist;
	}

	public void setNephrologist(Nephrologist nephrologist) 
	{
		this.nephrologist = nephrologist;
	}	

	@Override
	public String toString() 
	{
		return "NephrologistBaseClinic [id=" + id + ", salary=" + salary + ", dedicationHours=" + dedicationHours
				+ ", clinic=" + clinic + ", nephrologist=" + nephrologist + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clinic == null) ? 0 : clinic.hashCode());
		result = prime * result + dedicationHours;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nephrologist == null) ? 0 : nephrologist.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		NephrologistBaseClinic other = (NephrologistBaseClinic) obj;
		if (clinic == null) {
			if (other.clinic != null)
				return false;
		} else if (!clinic.equals(other.clinic))
			return false;
		if (dedicationHours != other.dedicationHours)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nephrologist == null) {
			if (other.nephrologist != null)
				return false;
		} else if (!nephrologist.equals(other.nephrologist))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
	}			
}
