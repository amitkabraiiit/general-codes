package com.practice.design;

class Logger{
	
	private static Logger logger = null;
	static String name;
	int level;
	private Logger() {
	}
	static Logger getLogger(String name){
		if(logger == null){
			logger = new Logger();
		}
		return logger;
	}
	void info(String msg){
		if(level > 0)	System.out.println("INFO : "+name+" : "+msg); // level 1
	}
	void debug(String msg){
		if(level > 1)System.out.println("DEBUG : "+name+" : "+msg); // level 2
	}
	void warn(String msg){
		if(level > 2)System.out.println("WARN : "+name+" : "+msg); // level 3
	}
	void setLevel(int level){
		this.level = level;
	}
}

public class Log4j {
	Logger log = Logger.getLogger("Test");

	
	public static void main(String[] args) {
/*		log.setLevel(2);
		log.info("This should be info");		
		log.debug("This should be debug");
		log.warn("This should be warn");*/

	}
	
}
