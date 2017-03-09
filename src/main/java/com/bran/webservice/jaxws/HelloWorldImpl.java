package com.bran.webservice.jaxws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.bran.webservice.jaxws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String getHelloWorldAsString(String name) {
		// TODO Auto-generated method stub
		return "Hello World JAX-SW, " + name;
	}

}
