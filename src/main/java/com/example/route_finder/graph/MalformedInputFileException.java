package com.example.route_finder.graph;

import java.nio.file.Path;

import lombok.ToString;

@ToString
public class MalformedInputFileException extends RuntimeException {

	public MalformedInputFileException(Path inputFilePath, String line) {
		this.inputFilePath = inputFilePath;
		this.line = line;
	}

	private static final long serialVersionUID = 6260738284852528675L;
	
	private Path inputFilePath;
	
	private String line;

}
