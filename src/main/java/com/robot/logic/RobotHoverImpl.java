package com.robot.logic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.robot.model.Input;
import com.robot.model.Output;
import com.robot.model.Robot;
import com.robot.model.Room;
import com.robot.utility.ObjectGenerator;
import com.robot.utility.ObjectGeneratorImpl;

/**
 * Generate final output with input.
 */
public class RobotHoverImpl implements RobotHover {
	
	@Override
	public Output generateOutput(Input input) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationcontext.xml");
		
		ObjectGenerator objGen = context.getBean(ObjectGeneratorImpl.class);
		
		Robot robot = objGen.generateRobot(input);
		Room room = objGen.generateRoom(input);
		
		hover(robot, room);
		while (!robot.isInstructionsFinished()) {
			moveRobotOneStep(robot, room);
			hover(robot, room);
		}
		
		return new Output(new int[] {robot.getX(), robot.getY()}, 
				robot.getHoveredPatchesCount());
	}
	
	void moveRobotOneStep(Robot robot, Room room) {
		switch(robot.nextInstruction()) {
			case "E": {
				if (robot.getX() < room.getColumn() - 1)
					robot.goEast(); 
				break;
			}
			case "S": {
				if (robot.getY() > 0)
					robot.goSouth();
				break;
			}
			case "W": {
				if (robot.getX() > 0)
					robot.goWest();
				break;
			}
			case "N": {
				if (robot.getY() < room.getRow() - 1)
					robot.goNorth(); 
				break;
			}
		}
	}
	
	void hover(Robot robot, Room room) {
		int x = robot.getX();
		int y = robot.getY();
		
		if (x < 0 || x >= room.getRow() || y < 0 || y >= room.getColumn())
			return;
		
		if (room.getPatchesMatrix()[x][y] == 1) {
			room.removePatch(x, y);
			robot.increasePatchCount();
		}
	}
}
