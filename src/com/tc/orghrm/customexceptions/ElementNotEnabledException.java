package com.tc.orghrm.customexceptions;

public class ElementNotEnabledException extends RuntimeException{
	
	public ElementNotEnabledException(String message) {
		super(message);
	}
}
