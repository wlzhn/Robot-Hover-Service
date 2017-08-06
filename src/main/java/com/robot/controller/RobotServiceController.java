package com.robot.controller;

import java.net.UnknownHostException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.dao.DatabaseWriter;
import com.robot.dao.MongoDBWriterImpl;
import com.robot.exception.InvalidInputException;
import com.robot.logic.RobotHover;
import com.robot.logic.RobotHoverImpl;
import com.robot.model.ErrorResponse;
import com.robot.model.Input;
import com.robot.model.Output;
import com.robot.utility.InputValidatorImpl;
import com.robot.utility.InputValidatorImpl2;
import com.robot.utility.InputValidator;
import com.robot.utility.InputValidator2;

/**
 * Handles requests for the robot hovering service.
 */
@Controller
public class RobotServiceController {
	
	@RequestMapping(value = "rest/robot/version", method = RequestMethod.GET)
	public @ResponseBody String getVersion() {
		return "1.0";
	}
	
	@RequestMapping(value = "rest/robot/hover", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Output> hoverService(@RequestBody Input input) throws InvalidInputException, UnknownHostException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationcontext.xml");
		
		//Write user input into MongoDB.
		DatabaseWriter dbConnector = context.getBean(MongoDBWriterImpl.class);
		dbConnector.writeToDatabase(input);
		
		//Validate user input. If input is not valid, return error message to user.
		InputValidator2 validator = context.getBean(InputValidatorImpl2.class);
		String result = validator.validateInput(input);
		if (result != "")
			throw new InvalidInputException(result);
		
		//If user input is valid, calculate output.
		RobotHover robotHover = context.getBean(RobotHoverImpl.class);
		Output output = robotHover.generateOutput(input);
		
		//Write output into MongoDB.
		dbConnector.writeToDatabase(output);
		
		return new ResponseEntity<Output>(output, HttpStatus.OK);
	}
	
	@RequestMapping(value = "rest/robot/hovernodb", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Output> hoverServiceNoDB(@RequestBody Input input) throws InvalidInputException, UnknownHostException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationcontext.xml");
		
		/*//Write user input into MongoDB.
		DatabaseWriter dbConnector = context.getBean(MongoDBWriterImpl.class);
		dbConnector.writeToDatabase(input);
		*/
		
		//Validate user input. If input is not valid, return error message to user.
		InputValidator2 validator = context.getBean(InputValidatorImpl2.class);
		String result = validator.validateInput(input);
		if (result != "")
			throw new InvalidInputException(result);
		
		//If user input is valid, calculate output.
		RobotHover robotHover = context.getBean(RobotHoverImpl.class);
		Output output = robotHover.generateOutput(input);
		
		/*//Write output into MongoDB.
		dbConnector.writeToDatabase(output);
		*/
		
		return new ResponseEntity<Output>(output, HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}
