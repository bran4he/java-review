package com.bran.webservice.cfx;

import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public final class Client {

    private static final QName SERVICE_NAME 
        = new QName("http://cfx.webservice.bran.com/", "HelloWorld");
    private static final QName PORT_NAME 
        = new QName("http://cfx.webservice.bran.com/", "HelloWorldPort");


    private Client() {
    } 

    public static void test0(){

        Service service = Service.create(SERVICE_NAME);
        // Endpoint Address
        String endpointAddress = "http://localhost:9000/helloWorld";
        // If web service deployed on Tomcat (either standalone or embedded)
        // as described in sample README, endpoint should be changed to:
        // String endpointAddress = "http://localhost:8080/java_first_jaxws/services/hello_world";

        // Add a port to the Service
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        
        HelloWorld hw = service.getPort(HelloWorld.class);
        System.out.println(hw.sayHi("World"));

        User user = new UserImpl("World");
        System.out.println(hw.sayHiToUser(user));

        //say hi to some more users to fill up the map a bit
        user = new UserImpl("Galaxy");
        System.out.println(hw.sayHiToUser(user));

        user = new UserImpl("Universe");
        System.out.println(hw.sayHiToUser(user));

        System.out.println();
        System.out.println("Users: ");
        Map<Integer, User> users = hw.getUsers();
        for (Map.Entry<Integer, User> e : users.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue().getName());
        }

    
    }
//    public static void test1(){
//    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//    	factory.getInInterceptors().add(new LoggingInInterceptor());
//    	factory.getOutInterceptors().add(new LoggingOutInterceptor());
//    	factory.setServiceClass(HelloWorld.class);
//    	factory.setAddress("http://localhost:9000/helloWorld");
//    	HelloWorld client = (HelloWorld) factory.create();
//    	 
//    	String reply = client.sayHi("HI");
//    	System.out.println("Server said: " + reply);
//    	System.exit(0);
//    }
    
    public static void main(String args[]) throws Exception {
    	test0();
    }

}
