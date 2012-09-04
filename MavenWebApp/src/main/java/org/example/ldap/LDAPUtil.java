package org.example.ldap;

import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

@Service("ldapUtil")
public class LDAPUtil {

	Logger log = LoggerFactory.getLogger(LDAPUtil.class);
	
	@Autowired
	private LdapTemplate ldapTemplate;

	@Value( "${ldap.url}" )
	private String ldapurl;

	@Value( "${ldap.base}" )
	private String ldapbase;
	
	@Value( "${ldap.userDn}" )
	private String ldapuserDn;
	
	@Value( "${ldap.password}" )
	private String ldappassword;
	
	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	private class PersonAttributesMapper implements AttributesMapper {
	      public String mapFromAttributes(Attributes attrs) throws NamingException {
	         
	    	  StringBuffer sb = new StringBuffer();
	    	  NamingEnumeration<? extends Attribute> enumeration = attrs.getAll();
	    	  
	    	  while (enumeration.hasMore()) {
	    		  Attribute attr = (Attribute)enumeration.next();
	    		  String id = attr.getID();
	    		  NamingEnumeration<?> valuesEnum = attr.getAll();
	    		  while (valuesEnum.hasMore()) {
	    			  Object value = valuesEnum.next();
	    			  
	    			  if (id.equalsIgnoreCase("userPassword")) {
	    				  sb.append("\n<Attribute ID=\"" + id + " Value=\"" + new String((byte[])value) + "\" />\n");
	    			  }
	    			  else {
	    				  sb.append("\n<Attribute ID=\"" + id + " Value=\"" + (String)value + "\" />\n");
	    			  }
	    		  }
	    	  } 
	    	  
	         return sb.toString();
	      }
	 }

	 
	 public List getAllPersonNames() {
	      return ldapTemplate.search(
	         "", "(objectclass=person)",
	         new AttributesMapper() {
	            public Object mapFromAttributes(Attributes attrs)
	               throws NamingException {
	               return attrs.get("cn").get();
	            }
	         });
	 }
	 
	 public String getPersonDetail(String dn) {
		 log.info("getPersonDetail : dn = " + dn);
		 log.info("ldap.url = " + ldapurl);
		 log.info("ldap.base = " + ldapbase);
		 log.info("ldap.userDn = " + ldapuserDn);
		 log.info("ldap.password = " + ldappassword);
		 return (String) ldapTemplate.lookup(dn, new PersonAttributesMapper());
	 }
	 
	 public boolean authenicate(String dn, String password) {
		 log.info("authenicate");
		 
		 return ldapTemplate.authenticate("", dn, password);
	 }
	 
}
