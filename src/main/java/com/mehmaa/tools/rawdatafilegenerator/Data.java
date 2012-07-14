package com.mehmaa.tools.rawdatafilegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification for a data row
 * 
 * @author mehdimaachou
 * 
 */
public class Data {

    private int totalrecords;
    private List<Field> fields = new ArrayList<Field>();

    public int getTotalrecords() {
	return totalrecords;
    }

    public void setTotalrecords(int totalrecords) {
	this.totalrecords = totalrecords;
    }

    public List<Field> getFields() {
	return fields;
    }

    public void setFields(List<Field> fields) {
	this.fields = fields;
    }

}
