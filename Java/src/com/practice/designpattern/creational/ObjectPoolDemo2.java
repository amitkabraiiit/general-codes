package com.practice.designpattern.creational;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

// Source : 
// https://www.geeksforgeeks.org/object-pool-design-pattern/
// http://codepumpkin.com/object-pool-design-pattern/



abstract class ObjectPooll<T> {
	private long expirationTime;

	private Hashtable<T, Long> locked, unlocked;

	public ObjectPooll() {
		expirationTime = 30000; // 30 seconds
		locked = new Hashtable<T, Long>();
		unlocked = new Hashtable<T, Long>();
	}

	protected abstract T create();

	public abstract boolean validate(T o);

	public abstract void expire(T o);

	public synchronized T getObject() {
		long now = System.currentTimeMillis();
		T t;
		if (unlocked.size() > 0) {
			Enumeration<T> e = unlocked.keys();
			while (e.hasMoreElements()) {
				t = e.nextElement();
				if ((now - unlocked.get(t)) > expirationTime) {
					// object has expired
					unlocked.remove(t);
					expire(t);
					t = null;
				} else {
					if (validate(t)) {
						unlocked.remove(t);
						locked.put(t, now);
						return (t);
					} else {
						// object failed validation
						unlocked.remove(t);
						expire(t);
						t = null;
					}
				}
			}
		}
		// no objects available, create a new one
		t = create();
		locked.put(t, now);
		return (t);
	}

	public synchronized void releaseObject(T t) {
		locked.remove(t);
		unlocked.put(t, System.currentTimeMillis());
	}
}

class JDBCConnectionPool extends ObjectPooll<Connection> {

	private String url, usr, pwd;

	public JDBCConnectionPool(String driver, String url, String usr, String pwd) {
		super();
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
	}

	@Override
	protected Connection create() {
		try {
			return (DriverManager.getConnection(url, usr, pwd));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	@Override
	public void expire(Connection o) {
		try {
			((Connection) o).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validate(Connection o) {
		try {
			return (!((Connection) o).isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}
}

public class ObjectPoolDemo2 {
	public static void main(String[] args) {
		// Create the ConnectionPool:
		JDBCConnectionPool pool = new JDBCConnectionPool(
				"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mydb",
				"root", "root");

		// Get a connection:
		Connection connection = pool.getObject();

		// Use the connection
		//...

		// Return the connection:
		pool.releaseObject(connection);
	}
}
