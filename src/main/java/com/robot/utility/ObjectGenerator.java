package com.robot.utility;

import com.robot.model.Input;
import com.robot.model.Robot;
import com.robot.model.Room;

public interface ObjectGenerator {
	Robot generateRobot(Input input);
	Room generateRoom(Input input);
}
