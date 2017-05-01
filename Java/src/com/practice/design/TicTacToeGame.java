package com.practice.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TicTacToeGame {

	int[] count;
	int size = 0;

	/** Initialize your data structure here. */
	public TicTacToeGame(int n) {
		count = new int[2 * n + 2];
		size = n;
	}

	public int move(int row, int col, int player) {
		int sign = (player == 1) ? 1 : -1;
		count[row] += sign;
		count[size + col] += sign;
		if (row == col) count[2 * size] += sign;
		if (row + col == size - 1) 	count[2 * size + 1] += sign;
		if (Math.abs(count[row]) == size || Math.abs(count[size + col]) == size
				|| Math.abs(count[2 * size]) == size || Math.abs(count[2 * size + 1]) == size) {
			return player;    
		}
		return 0;
	}

	public List<Integer>getRandomValue(int n){
		List<Integer> l = new ArrayList<Integer>();
		for(int i=0;i<n;i++)l.add(i);
		System.out.println(Arrays.toString(l.toArray()));
		Collections.shuffle(l);
		System.out.println(Arrays.toString(l.toArray()));
		return l;
	}
	public static void main(String[] args) {
		int boardSize = 3;
		TicTacToeGame t = new TicTacToeGame(boardSize);
		List<Integer> l = t.getRandomValue(boardSize*boardSize);
		int position, player, result ;
		for(int i=0; i< boardSize*boardSize ; i++){
			position = l.get(i);
			int row = position/3;
			int col = position%3;
			if(i%2 ==0)player = 1;
			else player = 2;
			result = t.move(row, col, player);
			if(result == 0)	System.out.println("match in draw state");
			if(result !=0)
			{
				System.out.println("player "+player+" wins!!");
				break;
			}
		}
	}
}
