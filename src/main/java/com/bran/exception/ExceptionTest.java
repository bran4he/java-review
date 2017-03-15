package com.bran.exception;

public class ExceptionTest {

	public static void main(String[] args) {
		
		String str = "";
		str = null;
		try {
			if(str.contains("s")){
				System.out.println("test");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(getExceptionInfo(e));
		}
	}
	
	
	
	public static String getExceptionInfo(Exception ex){
		StringBuilder outStr = new StringBuilder();
		StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
        	outStr.append("\tat ").append(s).append("\r\n");
        }
        return outStr.toString();
	}
}
