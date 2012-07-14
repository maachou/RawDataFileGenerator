package com.mehmaa.tools.rawdatafilegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test for DataGeneratorTool
 * 
 * @author mehdimaachou
 * 
 */
public class DataGeneratorTest {

    DataGeneratorTool generatorTool;
    Field field01;
    Field field02;

    @Before
    public void setUp() throws Exception {
	this.generatorTool = new DataGeneratorTool("src/test/resources/inputTestFile.xml");
	field01 = new Field(1, "field_1", "Description field 1", "ALPHA", 20, "always", "variable");
	field02 = new Field(2, "field_2", "Description field 2", "ALPHANUM", 10, "random", "full");
    }

    /**
     * Testing checkWellFormedness() method
     */
    @Test
    public void checkWellFormednessTest() {
	Assert.assertTrue(this.generatorTool.checkWellFormedness());
    }

    /**
     * Testing validateAgaintXsd() method
     */
    @Test
    public void validateAgaintXsdTest() {
	Assert.assertTrue(this.generatorTool.validateAgaintXsd());
    }

    /**
     * Testing getSpecModel() method
     */
    @Test
    public void getSpecModelTest() {
	Data result = this.generatorTool.getSpecModel();
	Assert.assertTrue(result.getTotalrecords() == 50);
	Assert.assertTrue(result.getFields().size() == 2);
	Assert.assertEquals(field01, result.getFields().get(0));
	Assert.assertEquals(field02, result.getFields().get(1));
    }
}
