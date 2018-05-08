package com.practice.designpattern.creational;

class SingletonNotThreadSafe{
	private static SingletonNotThreadSafe singletonExample = null;
	private SingletonNotThreadSafe() {
	}
	public static SingletonNotThreadSafe getInstance() {
		if (singletonExample == null) {
			singletonExample = new SingletonNotThreadSafe();
		}
		return singletonExample;
	}
	public void sayHello() {
		System.out.println("Hello");
	}

}

class StaticBlockSingleton {
    private static StaticBlockSingleton instance;
    private StaticBlockSingleton(){}
    //static block initialization for exception handling
    static{
        try{
            instance = new StaticBlockSingleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}

class SingletonDCL{
	private volatile SingletonDCL instance = null;
	private SingletonDCL(){
	}
	public SingletonDCL getInstance(){
		if(instance == null) {
			synchronized (this) {
				if(instance == null) {
					instance = new SingletonDCL();		
				}
			}

		}
		return instance;
	}
}

// You can access it by EasySingleton.INSTANCE, much easier than calling getInstance() method on Singleton.
enum EasySingleton{
    INSTANCE;
}

/*
 *  Same as above
 *  enum fields are compile time constants, but they are instances of their enum type.
 *  And, they're constructed when the enum type is referenced for the first time.
 *  Understand it like this : 
 *  public final class MySingleton {
    	public final static MySingleton INSTANCE = new MySingleton(); 
	}
 */

enum MySingleton {
    INSTANCE;
    private MySingleton() {
        System.out.println("Here");
    }
}

class Singleton{
	public static void main(String[] args) {
		SingletonNotThreadSafe singletonExample = SingletonNotThreadSafe.getInstance();
		singletonExample.sayHello();
		System.out.println(MySingleton.INSTANCE);
	}
}
