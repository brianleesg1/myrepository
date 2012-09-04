package org.example;
 
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.model.Person;
import org.example.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 
public class HelloServlet extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6446827937200082683L;

	Logger log = LoggerFactory.getLogger(HelloServlet.class);
	
	private PersonService personService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		log.info("HelloServlet doGet");

		ServletContext servletContext = this.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		personService = (PersonService)wac.getBean("personService");
		
		Person person = new Person();
		person.setFirst_name("Lee");
		person.setLast_name("Brian");
		
		person = personService.create(person);
		
		log.info("persion id created -> " + person.getEmplid());
		
		log.info("person -> " + personService.findByEmplid(person.getEmplid()));
		
		personService.delete(person);
		
		log.info("person deleted id -> " + person.getEmplid());
		
		response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello Servlet</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }
}