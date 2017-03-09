package com.bran.webservice.jaxws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		URL url = new URL("http://localhost:9999/ws/hello?wsdl");
		
		//http://jaxws.webservice.bran.com/
		QName qname = new QName("http://jaxws.webservice.bran.com/", "HelloWorldImplService");
		
		Service service = Service.create(url, qname);
		HelloWorld hello = service.getPort(HelloWorld.class);
		System.out.println(hello.getHelloWorldAsString("brandon"));
	}

}
