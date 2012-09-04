package org.example.service;

import java.util.List;

import org.example.model.Person;
import org.example.springjpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	 private PersonRepository personRepository;
	
	@Override
	public Person findByEmplid(Long emplid) {
		return personRepository.findByEmplid(emplid);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}
	
	@Override
	public Person create(Person person) {
		
		return personRepository.saveAndFlush(person);
	}

	@Override
	public void delete(Person person) {
		
		personRepository.delete(person);
		
	}
}
