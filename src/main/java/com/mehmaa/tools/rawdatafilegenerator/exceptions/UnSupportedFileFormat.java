package com.mehmaa.tools.rawdatafilegenerator.exceptions;

/**
 * Custom Exception for unsupported file extensions
 * 
 * @author mehdimaachou
 * 
 */
@SuppressWarnings("serial")
public class UnSupportedFileFormat extends Exception {

    public UnSupportedFileFormat() {
	super("Input file not supported");
    }
}
