package com.practice.design;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

enum Lamp { 
	/** 
	 * | N | 
	 * |   | 
	 * |   | 
	 * |   | 
	 * |   | 
	 * ------------------
	 * W   E 
	 * ------------------ 
	 * |   | 
	 * |   | 
	 * |   | 
	 * |   | 
	 * | S | 
	 * */ 
	//Express12A line lamp 

	S2N("N2S","S2W",false),
	S2W("N2E","E2W",false),
	E2W("W2E","E2S",false),
	E2S("W2N","S2N",false), 
	N2S(null,null,false), // I think not needed
	N2E(null,null,false), // I think not needed
	W2E(null,null,false), // I think not needed
	W2N(null,null,false), // I think not needed
	S2E(null,null,true),
	E2N(null,null,true),
	N2W(null,null,true),
	W2S(null,null,true); 

	//The corresponding direction lamp 
	private String oppsite; 
	//The lamp to light the next 
	private String next; 
	//The current state, trueExpressed as green 
	private boolean light; 

	Lamp(String oppsite,String next,boolean light){ 
		this.oppsite = oppsite; 
		this.next = next; 
		this.light = light; 
	} 

	//lightUpSaid the light 
	public void lightUp(){ 
		light = true; 
		System.out.println(name()+ " The green light"); 
		//If the lamp is not empty, At the same time, change direction lamp is corresponding to the state 
		if(oppsite != null) 
			Lamp.valueOf(oppsite).lightUp(); 
	} 

	//lightDownSaid the red light 
	public Lamp lightDown(){ 
		light = false; 
		System.out.println(name()+ " The red light"); 

		//If the lamp is not empty, At the same time, change direction lamp is corresponding to the state 
		if(oppsite != null) Lamp.valueOf(oppsite).lightDown(); 

		//Returns the next direction of the lamp 
		if(next != null) return Lamp.valueOf(next);
		return null; 
	}
	//Get the current state 
	public boolean isLight(){ 
		return light;  
	}  
}

class LampControler { 
	//Said the first light cycle control,Also for the light 
	Lamp currentLamp = Lamp.S2N; 

	//Provides methods to control of traffic lights 
	public void start(){ 

		//The lights green 
		currentLamp.lightUp(); 

		//Timer 
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1); 

		//Every10Second change status 
		timer.scheduleAtFixedRate( 
				new Runnable(){ 
					public void run(){ 
						//The lamp dimming, And get the next set of lights to quote 
						currentLamp = currentLamp.lightDown(); // the next set of lights green 
						currentLamp.lightUp(); 
					} 
				}, 10,10,TimeUnit.SECONDS); 
	} 
}

class Road { 

	//Route name 
	private String name; 
	// storage of vehicles set 
	private List<String> vechicles = new LinkedList<String>(); 

	public Road(String name){ 
		this.name = name; 

		//A thread pool, Every1-5Seconds to produce a car 
		ExecutorService pool = Executors.newSingleThreadExecutor(); 

		pool.execute(new Runnable(){ 
			public void run(){ 
				for(int i = 0 ; i <1000 ; i ++){ 
					try { 
						Thread.sleep((new Random().nextInt(5)+1)*1000); 
					} catch (InterruptedException e) { 
						e.printStackTrace(); 
					} 
					vechicles.add(Road.this.name+"_"+i); 
				} 
			} 
		}); 

		//Timer 
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1); 
		//Every second to determine the state of a current line lamp 
		timer.scheduleAtFixedRate( 
				new Runnable(){ 
					public void run(){ 
						//If a car on the road 
						if(vechicles.size() > 0){ 
							//If the lights are green 
							if(Lamp.valueOf(Road.this.name).isLight()){ 
								//Through a car 
								String car = vechicles.remove(0); 
								System.out.println(car+" has passed"); 
							} 
						} 
					} 
				}, 	1,	1,	TimeUnit.SECONDS); 
	} 
}

public class TrafficSignal2 { 
	
	/*
	 * 1. LampController will start with any lamp 
	 * 2. Lit the lamp green , if opposite exist then lit opposite also green
	 * 3. Every 10 seconds it will make current lamp red and then take the next Lamp and will do #2.
	 * 4. Each of the 12 Roads has two threads, one of which is producing cars on it at variable interval of 1-5 seconds.
	 * 5. Each of the 12 Roads has two threads, 2nd of which will pass car from it every second provided light is green and road has cars
	 * 6. Overall , road is producing (1-5 seconds) and consuming (every second) cars and lamp post is changing lights (every 10 seconds)
	 * 7. main methods starts the controller and initializes the road objects which in turn starts producer consumer threads in constructor. 
	 */
	
	public static void main(String[] args) { 
		// create 12 routes the instance of 
		Lamp[] lamps = Lamp.values(); 
		for(int i = 0 ; i <lamps.length ; i ++){ 
			new Road(lamps[i].name()); 
		}
		//The controller starts to work 
		new LampControler().start(); 

	} 	
}


