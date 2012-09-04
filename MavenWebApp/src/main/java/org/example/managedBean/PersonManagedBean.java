package org.example.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;

import org.example.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name="personMB")
@RequestScoped
public class PersonManagedBean {

	Logger log = LoggerFactory.getLogger(PersonManagedBean.class);

	@ManagedProperty("#{personService}")
    PersonService personService;
	
	@Size(min=5, max=25, message="firstname must be between 5 and 25")
	private String firstname;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
		
		personService.findAll();
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.personService = personService;
	}
	
	
}
