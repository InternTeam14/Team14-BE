package com.be.utils;

public class StorageException extends RuntimeException{

	public StorageException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public StorageException(String string, Exception e) {
		super(string,e);
	}


}
