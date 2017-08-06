package com.robot.dao;

import com.robot.model.Input;
import com.robot.model.Output;

public interface DatabaseWriter {
	void writeToDatabase(Input input);
	void writeToDatabase(Output output);
}
