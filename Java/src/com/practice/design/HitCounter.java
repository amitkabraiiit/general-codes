package com.practice.design;

public class HitCounter {
	private int[] times;
	private int[] hits;
	private int MAX_SIZE = 86400;
	private int lastHitIndex;
	/** Initialize your data structure here. */
	public HitCounter() {
		times = new int[MAX_SIZE];
		hits = new int[MAX_SIZE];
	}

	/** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
	public void hit(int timestamp) {
		lastHitIndex = timestamp % MAX_SIZE;
		if (times[lastHitIndex] != timestamp) {
			times[lastHitIndex] = timestamp;
			hits[lastHitIndex] = 1;
		} else {
			hits[lastHitIndex]++;
		}
	}

	/** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
	public int getHits(int basetime) {
		int total = 0;
		int count=0;
		int indexToConsider = 0;
		while(count!=basetime){
			indexToConsider = (lastHitIndex - count) < 0 ? (MAX_SIZE-1) : lastHitIndex - count;
			total += hits[indexToConsider];
			count++;
		}
		return total;
	}

	private int getHitsLastSecond(){
		return getHits(1) ;
	}
	private int getHitsLast1min(int timestamp){
		return getHits(60) ;
	}
	private int getHitsLast5min(int timestamp){
		return getHits(300) ;
	}
	private int getHitsLast1hour(int timestamp){
		return getHits(MAX_SIZE) ;
	}
	private int getHitsPer(int seconds){ // call getHitsPer(60) , getHitsPer(3600),getHitsPer(86400) to get hit per sec,min,hour
		int total = 0;
		int count=0;
		int indexToConsider = 0;
		while(count!=seconds){
			indexToConsider = (lastHitIndex - count) < 0 ? (MAX_SIZE-1) : lastHitIndex - count;
			total += hits[indexToConsider];
			count++;
		}
		return total/seconds;
	}	
}
