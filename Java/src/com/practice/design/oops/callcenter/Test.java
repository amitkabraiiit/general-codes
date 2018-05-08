package com.practice.design.oops.callcenter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CallHandler ch = new CallHandler();
		
/*		List<Call> callForRespondents = new ArrayList<>();
		callForRespondents.add(new Call(new Caller(1,"AmitR1")));
		callForRespondents.add(new Call(new Caller(2,"AmitR2")));
		
		List<Call> callForManagers = new ArrayList<>();
		callForManagers.add(new Call(new Caller(3,"AmitM1")));
		callForManagers.add(new Call(new Caller(4,"AmitM2")));
		
		List<Call> callForDirectors = new ArrayList<>();
		callForDirectors.add(new Call(new Caller(5,"AmitD1")));
		callForDirectors.add(new Call(new Caller(6,"AmitD2")));
		
		ch.callQueues.add(0,callForRespondents);
		ch.callQueues.add(1,callForManagers);
		ch.callQueues.add(2,callForDirectors);*/
		
		for(int i = 0 ; i< 10; i++){
			ch.dispatchCall(new Call(new Caller(i,"AmitM1")));
		}
	}

}
