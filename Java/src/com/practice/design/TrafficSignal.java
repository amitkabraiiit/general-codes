package com.practice.design;

import java.util.LinkedList;
import java.util.Queue;


enum RoadDirection {
	N , EN , S, WS , E , SE , W , NW ;
}
enum PedestrianDirection{
	W , E , N , S
}
enum TrafficColor {
	RED , YELLOW, GREEN
}
public class TrafficSignal {

	static Queue<String> q1 = new LinkedList<String>(); // RoadDirection.N , RoadDirection.EN , PedestrianDirection.W 
	static Queue<String> q2 = new LinkedList<String>(); // RoadDirection.S , RoadDirection.WS , PedestrianDirection.E
	static Queue<String> q3 = new LinkedList<String>(); // RoadDirection.E , RoadDirection.SE , PedestrianDirection.N
	static Queue<String> q4 = new LinkedList<String>(); // RoadDirection.W , RoadDirection.NW , PedestrianDirection.S
	static int queueNumber = 0;
	public static void changeSignalColor(TrafficColor c, Queue<String> q){

	}

	public static void sleep(long time){

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {}
	}

	public static void startSystem() throws InterruptedException{


		Runnable changeSignalColor = new Runnable() {
			@Override
			public void run() {
				int color = 0;
				while(true){
					sleep(2000);
					color = color % 4;
					System.out.println("Traffic light color "+color);
					if(color%4 ==0 ) queueNumber = 1;
					if(color%4 ==1 ) queueNumber = 2;
					if(color%4 ==2 ) queueNumber = 3;
					if(color%4 ==3 ) queueNumber = 4;
					color++;
				}
			}
		};

		Runnable comingRiders = new Runnable() {

			@Override
			public void run() {
				int count = 0;
				while(true){
					int rider = (int)System.currentTimeMillis() %4;
					sleep(1000);
					
					if(rider ==0 ) { q1.add("q1 rider"+count);System.out.println("q1 rider"+count+" came !!");}
					if(rider ==1 ) { q2.add("q2 rider"+count);System.out.println("q2 rider"+count+" came !!");}
					if(rider ==2 ) { q3.add("q3 rider"+count);System.out.println("q3 rider"+count+" came !!");}
					if(rider ==3 ) { q4.add("q4 rider"+count);System.out.println("q4 rider"+count+" came !!");}
					count++;
				}
			}
		};
		Runnable goingRiders = new Runnable() {

			@Override
			public void run() {
				while(true){
					sleep(900);
					if(queueNumber == 1) if (q1.size()!=0)System.out.println(q1.poll()+" going ");
					if(queueNumber == 2) if (q1.size()!=0)System.out.println(q1.poll()+" going ");
					if(queueNumber == 3) if (q1.size()!=0)System.out.println(q1.poll()+" going ");
					if(queueNumber == 4) if (q1.size()!=0)System.out.println(q1.poll()+" going ");
				}
			}
		};

		Thread t1 = new Thread(changeSignalColor);
		Thread t2 = new Thread(goingRiders);
		Thread t3 = new Thread(comingRiders);
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();

	}
	/*
	 * 4 signals
	 */

	public static void main(String[] args) throws InterruptedException {
		new TrafficSignal().startSystem();
	}

}
