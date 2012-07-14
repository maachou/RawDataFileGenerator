package com.mehmaa.tools.rawdatafilegenerator;

/**
 * Model representing a field that forms a data row
 * 
 * @author mehdimaachou
 * 
 */
public class Field {

    private int order;
    private String name;
    private String description;
    private String type;
    private int length;
    private Genproperties genproperties;

    /**
     * Constructor 1
     */
    public Field() {
    }

    /**
     * Contructor 2
     * 
     * @param order
     * @param name
     * @param description
     * @param type
     * @param length
     * @param genproperties
     */
    public Field(int order, String name, String description, String type, int length, Genproperties genproperties) {
	this.order = order;
	this.name = name;
	this.description = description;
	this.type = type;
	this.length = length;
	this.genproperties = genproperties;
    }

    /**
     * Constructor 3
     * 
     * @param order
     * @param name
     * @param description
     * @param type
     * @param length
     * @param valuepresence
     * @param datalength
     */
    public Field(int order, String name, String description, String type, int length, String valuepresence,
	    String datalength) {
	this.order = order;
	this.name = name;
	this.description = description;
	this.type = type;
	this.length = length;
	this.genproperties = new Genproperties(valuepresence, datalength);
    }

    /**** GETTERS / SETTERS ****/

    public int getOrder() {
	return order;
    }

    public void setOrder(int order) {
	this.order = order;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    public Genproperties getGenproperties() {
	return genproperties;
    }

    public void setGenproperties(Genproperties genproperties) {
	this.genproperties = genproperties;
    }

    /*** OVERRIDES ***/

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("FIELD: \n").append("Order: " + getOrder() + "\n").append("Name: " + getName() + "\n")
		.append("Desc.: " + getDescription() + "\n").append("Length: " + getLength() + "\n")
		.append("Type: " + getType() + "\n")
		.append("GenProperties.valuePresence: " + getGenproperties().getValuepresence() + "\n")
		.append("GenProperties.datalength: " + getGenproperties().getDatalength() + "\n");
	return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj instanceof Field) {
	    Field aField = (Field) obj;
	    return aField.order == getOrder() && aField.getName().equals(getName())
		    && aField.getDescription().equals(getDescription()) && aField.getLength() == getLength()
		    && aField.getType().equals(getType())
		    && aField.getGenproperties().getValuepresence().equals(getGenproperties().getValuepresence())
		    && aField.getGenproperties().getDatalength().equals(getGenproperties().getDatalength());
	} else
	    return false;
    }

}
