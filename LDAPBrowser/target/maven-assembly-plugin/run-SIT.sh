java -Dspring.profiles.active=SIT -Djavax.net.ssl.trustStore=/vrl/appl/appConf/SSLForJMS/key.jks -Djavax.net.ssl.trustStorePassword=pass1234 -Dssl.SocketFactory.provider=com.ibm.jsse2.SSLSocketFactoryImpl -Dssl.ServerSocketFactory.provider=com.ibm.jsse2.SSLSocketFactoryImpl -jar maven-assembly-plugin.jar

