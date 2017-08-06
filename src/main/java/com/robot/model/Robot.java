package com.robot.model;

/**
 * Represents a robot.
 */
public class Robot {
	private int x;
	private int y;
	private int hoveredPatchesCount;
	private String instructions;
	private int currentInstructionIndex;
	
	public Robot(int x, int y, String instructions) {
		this.x = x;
		this.y = y;
		this.instructions = instructions;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHoveredPatchesCount() {
		return hoveredPatchesCount;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	public void goEast() {
		x++;
	}
	
	public void goSouth() {
		y--;
	}
	
	public void goWest() {
		x--;
	}
	
	public void goNorth() {
		y++;
	}
	
	public void increasePatchCount() {
		hoveredPatchesCount++;
	}
	
	public boolean isInstructionsFinished() {
		if (instructions == null || instructions.isEmpty() 
				|| currentInstructionIndex == instructions.length())
			return true;
		return false;
	}
	
	public String nextInstruction() {
		if (instructions == null || currentInstructionIndex == instructions.length())
			return "";
		String nextInstruction = String.valueOf(instructions.charAt(currentInstructionIndex));
		currentInstructionIndex++;
		return nextInstruction;
	}
}
