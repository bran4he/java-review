package com.bran.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FutureTest {

	public static void main(String[] args) {
//		testCallableFuture();
//		testCallableFuture_();
		testCallableFuture_CompletionService();
//		testCallableFutureTask();
		
		
	}
	
	
	

	public static void testCallableFutureTask(){
		System.out.println("testCallableFutureTask");
		//#1
//		ExecutorService executor = Executors.newCachedThreadPool();
//		Task task = new Task();
//		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//		executor.submit(futureTask);
//		executor.shutdown();
		
		//#2
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		Thread thread = new Thread(futureTask);
		thread.start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main thread is running...");
		
		try {
			System.out.println("task running result:" + futureTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("all tasks is ended");
	}

	public static void testCallableFuture_(){
		System.out.println("testCallableFuture");
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newCachedThreadPool();	
		
		//#0
//		Future<Integer> result = executor.submit(new Callable<Integer>(){
//			@Override
//			public Integer call() throws Exception {
//				return ThreadLocalRandom.current().nextInt(100);
//			}
//		});
		
		
		//#1
//		Callable<Integer> call = () -> {
//			return ThreadLocalRandom.current().nextInt(100);
//		};
//		Future<Integer> result =executor.submit(call);
		
		//#2
		Future<Integer> result = (Future<Integer>) executor.submit(() -> ThreadLocalRandom.current().nextInt(100));
		
		
		executor.shutdown();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main thread is running...");
		
		try {
			System.out.println("task running result:" + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("all tasks is ended");
	
	}
	
	public static void testCallableFuture_CompletionService(){
		System.out.println("testCallableFuture_CompletionService");
		ExecutorService threadPool = Executors.newCachedThreadPool();	
		
		CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
		for(int i = 1; i<=5; i++){
			int taskId = i;
			cs.submit(() -> {
				System.out.println(taskId + ": thread is running...");
				TimeUnit.SECONDS.sleep(3);
				return taskId;
				});
		}
		threadPool.shutdown();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main thread is running...");
		
        // 可能做一些事情
        for(int i = 1; i <= 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
		
		System.out.println("all tasks is ended");
		
	}
	
	
	public static void testCallableFuture(){
		System.out.println("testCallableFuture");
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		Future<Integer> result = executor.submit(task);
		executor.shutdown();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main thread is running...");
		
		try {
			System.out.println("task running result:" + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("all tasks is ended");
	
	}
	
}

class Task implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sub thread is calculating...");
		TimeUnit.SECONDS.sleep(3);
		int sum = 0;
		for(int i = 0; i<100; i++){
			sum += i;
		}
		return sum;
	}
	
}