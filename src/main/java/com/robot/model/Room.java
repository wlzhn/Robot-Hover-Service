package com.robot.model;

/**
 * Represents the room.
 */
public class Room {
	private int row;
	private int column;
	
	private int[][] patchesMatrix;
	
	public Room(int row, int column, int[][] patches) {
		this.row = row;
		this.column = column;
		patchesMatrix = new int[row][column];
		for (int i = 0; i < patches.length; i++) {
			patchesMatrix[patches[i][0]][patches[i][1]] = 1;
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int[][] getPatchesMatrix() {
		return patchesMatrix;
	}
	
	public void removePatch(int x, int y) {
		patchesMatrix[x][y] = 0;
	}
}
