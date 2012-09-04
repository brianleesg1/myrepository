package org.example.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persons {
	
	@XmlElement
	private List<Person> person;

	
	public Persons() {
		super();
	}


	public Persons(List<Person> person) {
		super();
		this.person = person;
	}


	public List<Person> getPerson() {
		return person;
	}
	
	
}
