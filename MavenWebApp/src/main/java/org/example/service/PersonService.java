package org.example.service;

import java.util.List;

import org.example.model.Person;

public interface PersonService {

	/**
	 * Get Person with the associated emplid.
	 * @param emplid - Employee ID
	 * @return Person object if provided emplid is 
	 * found in the data repository, otherwise return null
	 */
	public Person findByEmplid(Long emplid);
	
	public List<Person> findAll();
	
	public Person create(Person person);
	
	public void delete(Person person);
}
