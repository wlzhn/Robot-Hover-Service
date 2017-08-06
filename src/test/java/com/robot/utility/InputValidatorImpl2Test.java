package com.robot.utility;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.robot.model.Input;

public class InputValidatorImpl2Test {
	
	private static Input input;
	private static InputValidatorImpl2 validator;
	
	@BeforeClass
	public static void init() {
		input = new Input();
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{1,0},{2,2},{2,3}});
		input.setInstructions("ESWN");
		
		validator = new InputValidatorImpl2();
	}
	
	@Test
	public void testValidateRoomSize1() {
		input.setRoomSize(new int[] {5, 5});
		assertEquals("", validator.validateRoomSize(input));
	}
	
	@Test
	public void testValidateRoomSize2() {
		input.setRoomSize(null);
		assertEquals("Invalid input. 'roomSize' field does not exist.", validator.validateRoomSize(input));
	}
	
	@Test
	public void testValidateRoomSize3() {
		input.setRoomSize(new int[] {5, 5, 1});
		assertEquals("Invalid input. Length of roomSize must be 2.", validator.validateRoomSize(input));
	}
	
	@Test
	public void testValidateRoomSize4() {
		input.setRoomSize(new int[] {-1, 5});
		assertEquals("Invalid input. roomSize's value must be positive.", validator.validateRoomSize(input));
	}
	
	@Test
	public void testValidateCoords1() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		assertEquals("", validator.validateCoords(input));
	}
	
	@Test
	public void testValidateCoords2() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(null);
		assertEquals("Invalid input. 'coords' field does not exist.", 
				validator.validateCoords(input));
	}
	
	@Test
	public void testValidateCoords3() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1});
		assertEquals("Invalid input. Length of coords must be 2.", 
				validator.validateCoords(input));
	}
	
	@Test
	public void testValidateCoords4() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {6, 2});
		assertEquals("Invalid input. coords's row value must be between 0 and 4.", 
				validator.validateCoords(input));
	}
	
	@Test
	public void testValidateCoords5() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 6});
		assertEquals("Invalid input. coords's column value must be between 0 and 4.", 
				validator.validateCoords(input));
	}
	
	@Test
	public void testValidatePatches1() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{1,0},{2,2},{2,3}});
		assertEquals("", validator.validatePatches(input));
	}
	
	@Test
	public void testValidatePatches2() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(null);
		assertEquals("Invalid input. 'peatches' field does not exist.", 
				validator.validatePatches(input));
	}
	
	
	@Test
	public void testValidatePatches3() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{1},{2, 3}});
		assertEquals("Invalid input. patches must have 2 columns.", 
				validator.validatePatches(input));
	}
	
	
	@Test
	public void testValidatePatches4() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{5, 0},{2, 3}});
		assertEquals("Invalid input. patches's row value must be between 0 and 4.", 
				validator.validatePatches(input));
	}
	
	
	@Test
	public void testValidatePatches5() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{2, 0},{2, 6}});
		assertEquals("Invalid input. patches's column value must be between 0 and 4.", 
				validator.validatePatches(input));
	}
	
	@Test
	public void testValidateInstructions1() {
		input.setInstructions("ESWN");
		assertEquals("", validator.validateInstructions(input));
	}
	
	@Test
	public void testValidateInstructions2() {
		input.setInstructions(null);
		assertEquals("Invalid input. 'instructions' field does not exist.", validator.validateInstructions(input));
	}
	
	@Test
	public void testValidateInstructions3() {
		input.setInstructions("EsWN");
		assertEquals("Invalid input. Instructions can only contain characters "
				+ "'E', 'S', 'W', 'N'.", validator.validateInstructions(input));
	}
}
