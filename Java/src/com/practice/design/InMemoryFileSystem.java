package com.practice.design;
import java.util.ArrayList;

/**
 * 8.9 Explain the data structures and algorithms that you would use to design
 * an in-memory file system. Illustrate with an example in code where possible.
 */

/*
 * A file system, consist of Files and Directories. Each Directory contains a
 * set of Files and Directories. Since Files and Directories share so many
 * characteristics, we have implemented them such that they inherit from the
 * same class, Entry.
 */

public class InMemoryFileSystem {
}


class File9 extends Entry {
	private String content;
	private int size;
	public File9(String n, Directory p, int sz) {
		super(n, p);
		size = sz;
	}

	public int size() {
		return size;
	}

	public String getContents() {
		return content;
	}

	public void setContents(String c) {
		content = c;
	}

}

abstract class Entry {
	protected Directory parent;
	protected long created;
	protected long lastUpdated;
	protected long lastAccessed;
	protected String name;

	public Entry(String n, Directory p) {
		name = n;
		parent = p;
		created = System.currentTimeMillis();
		lastUpdated = System.currentTimeMillis();
		lastAccessed = System.currentTimeMillis();
	}

	public boolean delete() {
		if (parent == null)
			return false;
		return parent.deleteEntry(this);
	}

	public abstract int size();

	public String getFullPath() {
		if (parent == null)
			return name;
		else
			return parent.getFullPath() + "/" + name;
	}

	// getters and setters
	public long getCreationTIme() {
		return created;
	}

	public long getLastUpdatedTime() {
		return lastUpdated;
	}

	public long getLastAccessedTime() {
		return lastAccessed;
	}

	public void changeName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

}

class Directory extends Entry {
	protected ArrayList<Entry> contents;

	public Directory(String n, Directory p) {
		super(n, p);
		contents = new ArrayList<Entry>();
	}

	public int size() {
		int size = 0;
		for (Entry e : contents) {
			size += e.size();
		}
		return size;
	}

	public int numberOfFiles() {
		int count = 0;
		for (Entry e : contents) {
			if (e instanceof Directory) {
				count++;// Directory counts as a file
				Directory d = (Directory) e;
				count += d.numberOfFiles();
			} else if (e instanceof File9) {
				count++;
			}
		}
		return count;
	}

	public boolean deleteEntry(Entry entry) {
		return contents.remove(entry);

	}

	public void addEntry(Entry entry) {
		contents.add(entry);

	}

	protected ArrayList<Entry> getContents() {
		return contents;
	}

}
