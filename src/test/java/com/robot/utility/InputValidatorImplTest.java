package com.robot.utility;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.robot.model.Input;

public class InputValidatorImplTest {
	
	private static Input input;
	private static InputValidatorImpl validator;
	
	@BeforeClass
	public static void init() {
		input = new Input();
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{1,0},{2,2},{2,3}});
		input.setInstructions("ESWN");
		
		validator = new InputValidatorImpl();
	}
	
	@Test
	public void testIsRoomSizeValid1() {
		input.setRoomSize(new int[] {5, 5});
		assertTrue(validator.isRoomSizeValid(input));
	}
	
	@Test
	public void testIsRoomSizeValid2() {
		input.setRoomSize(new int[] {-1, 5});
		assertFalse(validator.isRoomSizeValid(input));
	}
	
	@Test
	public void testIsRoomSizeValid3() {
		input.setRoomSize(new int[] {5});
		assertFalse(validator.isRoomSizeValid(input));
	}
	
	@Test
	public void testIsCoordsValid1() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		assertTrue(validator.isCoordsValid(input));
	}
	
	@Test
	public void testIsCoordsValid2() {
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {6, 2});
		assertFalse(validator.isCoordsValid(input));
	}
	
	@Test
	public void testIsPatchesValid1() {
		input.setRoomSize(new int[] {5, 5});
		input.setPatches(new int[][] {{1,0},{2,2},{2,3}});
		assertTrue(validator.isPatchesValid(input));
	}
	
	@Test
	public void testIsPatchesValid2() {
		input.setRoomSize(new int[] {5, 5});
		input.setPatches(new int[][] {{5,0},{2,2},{2,3}});
		assertFalse(validator.isPatchesValid(input));
	}
	
	@Test
	public void testIsPatchesValid3() {
		input.setRoomSize(new int[] {5, 5});
		input.setPatches(new int[][] {{5},{2,2},{2,3}});
		assertFalse(validator.isPatchesValid(input));
	}
	
	@Test
	public void testIsInstructionsValid1() {
		input.setInstructions("ESWN");
		assertTrue(validator.isInstructionsValid(input));
	}
	
	@Test
	public void testIsInstructionsValid2() {
		input.setInstructions("eSWN");
		assertFalse(validator.isInstructionsValid(input));
	}
	
	@Test
	public void testIsInstructionsValid3() {
		input.setInstructions(null);
		assertFalse(validator.isInstructionsValid(input));
	}
	
	@Test
	public void testIsInstructionsValid4() {
		input.setInstructions("E SWN");
		assertFalse(validator.isInstructionsValid(input));
	}
	
	@Test
	public void testValidateInput1() {
		input = new Input();
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{1,0},{2,2},{2,3}});
		input.setInstructions("ESWN");
		assertTrue(validator.validateInput(input));
	}
	
	@Test
	public void testValidateInput2() {
		input = new Input();
		input.setRoomSize(new int[] {5, -5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{1,0},{2,2},{2,3}});
		input.setInstructions("ESWN");
		assertFalse(validator.validateInput(input));
	}
}
