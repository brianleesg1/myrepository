package org.example.web.controller;

import java.util.List;

import org.example.model.Person;
import org.example.model.Persons;
import org.example.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
final class FooController {
	
	@Autowired
	PersonService personservice;
	
	Logger log = LoggerFactory.getLogger(FooController.class);
	
	@RequestMapping(value="person", method=RequestMethod.GET)
	@ResponseBody
	public final Persons getAll(@RequestHeader("Accept") String acceptHeader) {
		log.info("getAll() -> accept header : " + acceptHeader);
		return new Persons(personservice.findAll());
	}
	
	@RequestMapping(value="person/{id}", method=RequestMethod.GET)
	@ResponseBody
	public final Person get(@PathVariable("id") final Long id, @RequestHeader("Accept") String acceptHeader) {
		log.info("get() -> accept header : " + acceptHeader);
		log.info("get() -> id : " + id);
		return personservice.findByEmplid(id);
	}
	
}
