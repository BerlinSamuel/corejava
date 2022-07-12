package com.chainsys.miniproject.commonutil;

public class InvalidInputDataException extends RuntimeException{
	public InvalidInputDataException() {
		super("The Input Data is not Valid");
	}
	public InvalidInputDataException(String message) {
		super(message);
	}
}
