package com.practice.design;

import java.util.ArrayList;
import java.util.List;

abstract class Media{
	String name;
	public List<Song> songList = new ArrayList<Song>();

	public Media(String name) {
		this.name = name;
	}
	public void addSong(Song s) {	
		songList.add(s);
	}
	public List<Song> getSongs() {
		return songList;
	}
	public String getName() {
		return name;
	}

}
class CD extends Media{
	public CD(String name) {
		super(name);
	}
}
class USB extends Media{
	String name;
	public USB(String name) {
		super(name);
	}
}
// Similarly we can have radio 


interface MediaPlayer{
	public void play();
	public void listSongs();
}
class CDPlayer implements MediaPlayer{
	List<CD> cdList = new ArrayList<>();
	public CDPlayer() {
	}
	public void addCDToPlayer(CD cd){
		cdList.add(cd);
	}
	public void play() {
		for(CD c : cdList){
			System.out.println("Playing song from CD .... "+c.getName());
			for(Song s: c.getSongs()){
				System.out.println("Playing song : " + s.getSongName());
			}		
		}

	}
	@Override
	public void listSongs() {
		for(CD c : cdList){
			System.out.println("Listing song from CD .... "+c.getName());
			for(Song s: c.getSongs()){
				System.out.println("Listing song : " + s.getSongName());
			}		
		}
	}
}

// Similarly USBPlayer

class Song{
	String singer;
	String songName;
	public Song(String songName) {
		this.songName = songName;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
}

class JukeBox{
	CDPlayer cdPlayer = new CDPlayer();
	public void plugCD(CD cd){
		this.cdPlayer.addCDToPlayer(cd);
	}
	public void playCD(){
		cdPlayer.play();
	}
	public void listSongsCD(){
		cdPlayer.listSongs();
	}
}


public class JukeboxTest {

	/*
	 * Use cases
	 * 1) User add songs to CD/USB and then add CD/USB to JukeBox and play songs via CDPlayer/USBPlayer
	 * Actors
	 * 1) JukeBox has CDPlayer and USBPlayer.
	 * 2) User can plug CD and USB to JukeBox
	 * 3) USB and CD stores Songs and hence user can add songs to USB and CD
	 * 4) Song has name, singer, data associated with it.
	 */
	public static void main(String[] args) {
		JukeBox jb = new JukeBox();
		CD cd = new CD("Romantic CD");
		cd.addSong(new Song("Barsaat1"));
		cd.addSong(new Song("Barsaat2"));
		jb.plugCD(cd);
		jb.listSongsCD();
		jb.playCD();
	}
}
