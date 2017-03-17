package com.bran.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {

	public static void main(String[] args) {
		final Collection< Task > tasks = Arrays.asList(
			    new Task( Status.OPEN, 7 ),
			    new Task( Status.OPEN, 13 ),
			    new Task( Status.CLOSED, 8 ), 
			    new Task( Status.CLOSED, 20 ), 
			    new Task( Status.CLOSED, 2 ) 
			);
//		int openPoints = tasks.stream().filter(it -> it.status == Status.OPEN).mapToInt(it -> it.getPoints()).sum();
//		System.out.println("open taks points sum: " + openPoints);
		
//		int totalPoints = tasks.stream().parallel().mapToInt(it -> it.getPoints()).sum();
//		int totalPoints = tasks.stream().parallel().mapToInt(it -> it.getPoints()).reduce((sum, it) -> sum + it).getAsInt();
//		System.out.println("open taks points sum: " + totalPoints);
		
//		Map<Status, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(it -> it.getStatus()));
//		System.out.println("cat taks to map :" + map);
		
		int totalPoints = 50;
		List<String> result = tasks.stream()
			.mapToInt(Task::getPoints)
			.asLongStream()
			.mapToDouble(p -> p * 100/totalPoints)
			.peek(System.out::println)
			.boxed()
			.mapToLong(weight -> (long)(weight * 1))
			.mapToObj(percentage -> percentage + "%")
			.collect(Collectors.toList());
		System.out.println("summary of each tasks % :" +result);
	}
	
	
	
	private enum Status {
		OPEN, CLOSED
	};
	
	private static final class Task {
		private final Status status;
		private final Integer points;
		
		public Task(final Status status, final Integer points) {
			this.status = status;
			this.points = points;
		}

		public Status getStatus() {
			return status;
		}

		public Integer getPoints() {
			return points;
		}

		@Override
		public String toString() {
			return "Task [status=" + status + ", points=" + points + "]";
		}
		
		
		
	}
}
