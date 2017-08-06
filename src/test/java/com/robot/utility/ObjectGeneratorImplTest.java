package com.robot.utility;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.robot.model.Input;
import com.robot.model.Robot;
import com.robot.model.Room;

public class ObjectGeneratorImplTest {
	
	private static Input input;
	private static ObjectGenerator objGen;
	private static Robot robot;
	private static Room room;
	
	@BeforeClass
	public static void init() {
		input = new Input();
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		input.setPatches(new int[][] {{1,0},{2,2},{2,3}});
		input.setInstructions("ESWN");
		
		objGen = new ObjectGeneratorImpl();
		robot = objGen.generateRobot(input);
		room = objGen.generateRoom(input);
	}
	
	@Test
	public void testGenerateRobot1() {
		assertEquals(1, robot.getX());
	}
	
	@Test
	public void testGenerateRobot2() {
		assertEquals(2, robot.getY());
	}
	
	@Test
	public void testGenerateRobot3() {
		assertEquals("ESWN", robot.getInstructions());
	}
	
	@Test
	public void testGenerateRoom1() {
		assertEquals(5, room.getRow());
	}
	
	@Test
	public void testGenerateRoom2() {
		assertEquals(5, room.getColumn());
	}
}
