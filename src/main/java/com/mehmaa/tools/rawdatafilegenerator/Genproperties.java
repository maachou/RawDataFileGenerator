package com.mehmaa.tools.rawdatafilegenerator;

/**
 * Model representing the generation properties in a field
 * 
 * @author mehdimaachou
 * 
 */
public class Genproperties {

    private String valuepresence;
    private String datalength;

    /**
     * Contructor 1
     */
    public Genproperties() {

    }

    /**
     * Constructor 2
     * 
     * @param valuepresence
     * @param datalength
     */
    public Genproperties(String valuepresence, String datalength) {
	this.valuepresence = valuepresence;
	this.datalength = datalength;
    }

    /*** GETTERS/SETTERS ***/

    public String getValuepresence() {
	return valuepresence;
    }

    public void setValuepresence(String valuepresence) {
	this.valuepresence = valuepresence;
    }

    public String getDatalength() {
	return datalength;
    }

    public void setDatalength(String datalength) {
	this.datalength = datalength;
    }

}
