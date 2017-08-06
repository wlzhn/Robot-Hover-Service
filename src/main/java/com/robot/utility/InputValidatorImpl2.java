package com.robot.utility;

import com.robot.model.Input;

/**
 * Validates input and returns detailed results.
 */
public class InputValidatorImpl2 implements InputValidator2 {
	
	private String validateRoomSizeResult;
	
	@Override
	public String validateInput(Input input) {
		String result = validateRoomSize(input);
		if (result != "")
			return result;
		
		result = validateCoords(input);
		if (result != "")
			return result;
		
		result = validatePatches(input);
		if (result != "")
			return result;
		
		result = validateInstructions(input);
		if (result != "")
			return result;
		
		return "";
	}
	
	String validateRoomSize(Input input) {
		int[] roomSize = input.getRoomSize();
		
		if (roomSize == null) {
			validateRoomSizeResult = "Invalid input. 'roomSize' field does not exist.";
			return validateRoomSizeResult;
		}
		
		if (roomSize.length != 2) {
			validateRoomSizeResult = "Invalid input. Length of roomSize must be 2.";
			return validateRoomSizeResult;
		}
			
		
		for (int i : roomSize) {
			if (i <= 0) {
				validateRoomSizeResult = "Invalid input. roomSize's value must be positive.";
				return validateRoomSizeResult;
			}
		}
		
		validateRoomSizeResult = "";
		return validateRoomSizeResult;
	}
	
	String validateCoords(Input input) {
		
		if (validateRoomSizeResult == null) {
			if (validateRoomSize(input) != "")
				return validateRoomSizeResult;
		}
			
		int row = input.getRoomSize()[0];
		int column = input.getRoomSize()[1];
		int[] coords = input.getCoords();
		
		if (coords == null)
			return "Invalid input. 'coords' field does not exist.";
		
		if (coords == null || coords.length != 2)
			return "Invalid input. Length of coords must be 2.";
		
		if (coords[0] < 0 || coords[0] >= row)
			return "Invalid input. coords's row value must be between 0 and " + (row-1) + ".";
		
		if (coords[1] < 0 || coords[1] >= column)
			return "Invalid input. coords's column value must be between 0 and " + (column-1) + ".";
		
		return "";
	}
	
	String validatePatches(Input input) {
		
		if (validateRoomSizeResult == null) {
			if (validateRoomSize(input) != "")
				return validateRoomSizeResult;
		}
		
		int row = input.getRoomSize()[0];
		int column = input.getRoomSize()[1];
		
		int[][] patches = input.getPatches();
		
		if (patches == null)
			return "Invalid input. 'peatches' field does not exist.";
		
		for (int i = 0; i < patches.length; i++) {
			if (patches[i].length != 2)
				return "Invalid input. patches must have 2 columns.";
			
			if (patches[i][0] < 0 || patches[i][0] >= row)
				return "Invalid input. patches's row value must be between 0 and " + (row-1) + ".";
			
			if (patches[i][1] < 0 || patches[i][1] >= column)
				return "Invalid input. patches's column value must be between 0 and " + (column-1) + ".";
		}
		
		return "";
	}
	
	String validateInstructions(Input input) {
		
		String instructions = input.getInstructions();
		
		if (instructions == null)
			return "Invalid input. 'instructions' field does not exist.";
		
		String validInstructions = "ESWN";
		for (int i = 0; i < instructions.length(); i++) {
			if (!validInstructions.contains(String.valueOf(instructions.charAt(i))))
				return "Invalid input. Instructions can only contain characters "
						+ "'E', 'S', 'W', 'N'.";
		}
		
		return "";
	}
}
