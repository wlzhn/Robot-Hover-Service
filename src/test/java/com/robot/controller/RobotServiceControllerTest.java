package com.robot.controller;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.robot.exception.InvalidInputException;
import com.robot.model.ErrorResponse;
import com.robot.model.Input;
import com.robot.model.Output;

public class RobotServiceControllerTest {
	
	private static Input input;
	private static RobotServiceController controller;
	private static ResponseEntity<Output> output;
	
	@BeforeClass
	public static void initJSONObject() throws UnknownHostException, InvalidInputException {
		input = new Input();
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		int[][] patches = new int[][] {{1,0},{2,2},{2,3}};
		input.setPatches(patches);
		input.setInstructions("NNESEESWNWW");
		
		controller = new RobotServiceController();
		output = controller.hoverService(input);
	}
	
	@Test
	public void testHoverService() throws UnknownHostException, InvalidInputException {
		assertEquals(1, output.getBody().getCoords()[0]);
		
	}
	
	@Test
	public void testHoverService2() throws UnknownHostException, InvalidInputException {
		assertEquals(3, output.getBody().getCoords()[1]);
	}
	
	@Test
	public void testHoverService3() throws UnknownHostException, InvalidInputException {
		assertEquals(1, output.getBody().getPatches());
	}
	
	@Test(expected = InvalidInputException.class)
	public void testHoverService4() throws UnknownHostException, InvalidInputException {
		input.setRoomSize(new int[] {-1, 5});
		controller.hoverService(input);
	}
	
	@Test
	public void testExceptionHandler1() {
		ResponseEntity<ErrorResponse> errorResponse = 
				controller.exceptionHandler(new InvalidInputException());
		assertEquals(412, errorResponse.getBody().getErrorCode());
	}
	
	@Test
	public void testExceptionHandler2() {
		String errorMessage = "Invalid input.";
		ResponseEntity<ErrorResponse> errorResponse = 
				controller.exceptionHandler(new InvalidInputException(errorMessage));
		assertEquals(errorMessage, errorResponse.getBody().getMessage());
	}
	
}
