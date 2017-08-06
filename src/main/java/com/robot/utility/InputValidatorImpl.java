package com.robot.utility;

import com.robot.model.Input;

/**
 * Validates input and returns simple boolean result.
 */
public class InputValidatorImpl implements InputValidator {
	
	@Override
	public boolean validateInput(Input input) {
		if (!isRoomSizeValid(input) || !isCoordsValid(input)
				|| !isPatchesValid(input) || !isInstructionsValid(input))
			return false;
		return true;
	}
	
	boolean isRoomSizeValid(Input input) {
		int[] roomSize = input.getRoomSize();
		
		if (roomSize == null || roomSize.length != 2)
			return false;
		
		for (int i : roomSize) {
			if (i <= 0)
				return false;
		}
		
		return true;
	}
	
	boolean isCoordsValid(Input input) {
		if (!isRoomSizeValid(input))
			return false;
		
		int row = input.getRoomSize()[0];
		int column = input.getRoomSize()[1];
		int[] coords = input.getCoords();
		
		if (coords == null || coords.length != 2)
			return false;
		
		if (coords[0] < 0 || coords[0] >= row)
			return false;
		
		if (coords[1] < 0 || coords[1] >= column)
			return false;
		
		return true;
	}
	
	boolean isPatchesValid(Input input) {
		if (!isRoomSizeValid(input))
			return false;
		
		int row = input.getRoomSize()[0];
		int column = input.getRoomSize()[1];
		
		int[][] patches = input.getPatches();
		
		if (patches == null)
			return false;
		
		for (int i = 0; i < patches.length; i++) {
			if (patches[i].length != 2)
				return false;
			
			if (patches[i][0] < 0 || patches[i][0] >= row)
				return false;
			
			if (patches[i][1] < 0 || patches[i][1] >= column)
				return false;
		}
		
		return true;
	}
	
	boolean isInstructionsValid(Input input) {
		
		String instructions = input.getInstructions();
		
		if (instructions == null)
			return false;
		
		String validInstructions = "ESWN";
		for (int i = 0; i < instructions.length(); i++) {
			if (!validInstructions.contains(String.valueOf(instructions.charAt(i))))
				return false;
		}
		
		return true;
	}

}
