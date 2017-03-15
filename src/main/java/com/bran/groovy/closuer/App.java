package com.bran.groovy.closuer;


public class App {

	public static void main(String[] args) {
		App app = new App();
		app.cal(23);
		System.out.println(app.cc(23));
	}
	
	public void cal(int n){
		int sum =0;
		for(int i = 2; i<=n; i+=2){
			sum+=i;
		}
		System.out.println(sum);
	}
	
	
	public int cc(int n){
		if(n >= 2){
			if(n % 2 == 0){
				return n + cc(n-2);
			}else{
				return cc(n-1);
			}
		}
		return 0;

	}
}
