package com.practice.design.chess;

public class Pawn extends Piece{
	
	public Pawn(boolean available, int x, int y){
		super(available, x, y);
	}

	
	public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
		if(super.isValid(board, fromX, fromY, toX, toY) == false) {
			return false;
		}
		if(toX == (1 + fromX) && toY == fromY) {
			return true;
		}
		return false;
	}
}