package com.robot.logic;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.robot.model.Input;
import com.robot.model.Output;
import com.robot.model.Robot;
import com.robot.model.Room;
import com.robot.utility.ObjectGenerator;
import com.robot.utility.ObjectGeneratorImpl;

public class RobotHoverImplTest {
	
	private static Input input;
	private static RobotHoverImpl robotHoverImpl;
	private static ObjectGenerator objGen;
	private static Robot robot;
	private static Room room;
	
	@BeforeClass
	public static void init() {
		input = new Input();
		input.setRoomSize(new int[] {5, 5});
		input.setCoords(new int[] {1, 2});
		int[][] patches = new int[][] {{1,0},{2,2},{2,3}};
		input.setPatches(patches);
		input.setInstructions("NNESEESWNWW");
		
		robotHoverImpl = new RobotHoverImpl();
		objGen = new ObjectGeneratorImpl();
		robot = objGen.generateRobot(input);
		room = objGen.generateRoom(input);
	}
	
	@Test
	public void testGenerateOutput1() {
		assertEquals(1, robotHoverImpl.generateOutput(input).getCoords()[0]);
	}
	
	@Test
	public void testGenerateOutput2() {
		assertEquals(3, robotHoverImpl.generateOutput(input).getCoords()[1]);
	}
	
	@Test
	public void testGenerateOutput3() {
		assertEquals(1, robotHoverImpl.generateOutput(input).getPatches());
	}
	
	@Test
	public void testMoveRobotOneStep1() {
		robot = objGen.generateRobot(input);
		robotHoverImpl.moveRobotOneStep(robot, room);
		assertEquals(1, robot.getX());
	}
	
	@Test
	public void testMoveRobotOneStep2() {
		robot = objGen.generateRobot(input);
		robotHoverImpl.moveRobotOneStep(robot, room);
		assertEquals(3, robot.getY());
	}

	@Test
	public void testHover() {
		robot = objGen.generateRobot(input);
		robotHoverImpl.hover(robot, room);
		assertEquals(0, robot.getHoveredPatchesCount());
	}
}
