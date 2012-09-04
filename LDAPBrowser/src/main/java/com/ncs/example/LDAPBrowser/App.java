package com.ncs.example.LDAPBrowser;

import java.security.Security;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	static Logger log = LoggerFactory.getLogger(App.class);
	
	private static final String MODE_QUERY = "query";
	private static final String MODE_AUTHENICATE = "authenicate";
	
    public static void main( String[] args )
    {
    	log.info( "Hello World! 123" );
    	
    	if (args.length == 0) {
    		log.error("usage:");
    		log.error(MODE_QUERY + " <dn> example : " + MODE_QUERY + "  cn=LTANCSCC01");
    		log.error(MODE_AUTHENICATE + " <dn> <password> example : " + MODE_AUTHENICATE + " cn=LTANCSCC01 <password>");
    		System.exit(-1);
    	}
    	
    	String mode = args[0];
    	log.info("mode = " + mode);
    	String dn = null;
    	String password = null;
    	
    	if (mode.trim().equalsIgnoreCase(MODE_QUERY)) {
    		dn = args[1];
    		log.info("dn = " + dn);
    	}
    	else if (mode.trim().equalsIgnoreCase(MODE_AUTHENICATE)) {
    		dn = args[1];
    		log.info("dn = " + dn);
    		password = args[2];
    		log.info("password = " + password);
    	}
    	else {
    		log.error("undefined mode : " + mode);
    		System.exit(-1);
    	}
    	
    	
    	String sslSocketFactoryProvider = System.getProperty("ssl.SocketFactory.provider");
    	String sslServerSocketFactoryProvider = System.getProperty("ssl.ServerSocketFactory.provider");
    	
    	log.info("sslSocketFactoryProvider = " + sslSocketFactoryProvider);
    	log.info("sslServerSocketFactoryProvider = " + sslServerSocketFactoryProvider);
    	
    	if (sslServerSocketFactoryProvider != null) {
    		log.info("set ssl.SocketFactory.provider");
    		Security.setProperty("ssl.SocketFactory.provider", sslServerSocketFactoryProvider);
    	}
    	
    	if (sslServerSocketFactoryProvider != null) {
    		log.info("set ssl.ServerSocketFactory.provider");
    		Security.setProperty("ssl.ServerSocketFactory.provider", sslServerSocketFactoryProvider);
    	}
    	
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
 
		LDAP obj = (LDAP) context.getBean("ldap");
		
		if (mode.trim().equalsIgnoreCase(MODE_QUERY)) {
			if (dn.trim().equalsIgnoreCase("all")) {
				List list = obj.getAllPersonNames();
				log.error("list = " + list);
			}
			else {
				log.info(obj.getPersonDetail(dn));
			}
		}
		else if (mode.trim().equalsIgnoreCase(MODE_AUTHENICATE)) {
			boolean authenicate = obj.authenicate(dn, password);
			log.info("authenicate = " + authenicate);
		}
	
		log.info("exit");
    }
}
