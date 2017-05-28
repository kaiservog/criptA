package br.com.kaiservog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Info {

	@Id
	@SequenceGenerator( name = "seq_info", sequenceName = "seq_info", allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_info" )
	private Long id;
	
	private String service;
	private String value;
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
