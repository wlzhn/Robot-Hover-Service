package com.robot.utility;

import com.robot.model.Input;
import com.robot.model.Robot;
import com.robot.model.Room;

/**
 * Generates Robot and Room objects.
 */
public class ObjectGeneratorImpl implements ObjectGenerator {

	@Override
	public Robot generateRobot(Input input) {
		return new Robot(input.getCoords()[0], input.getCoords()[1], 
				input.getInstructions().toUpperCase());
	}

	@Override
	public Room generateRoom(Input input) {
		return new Room(input.getRoomSize()[0], input.getRoomSize()[1], 
				input.getPatches());
	}

}
