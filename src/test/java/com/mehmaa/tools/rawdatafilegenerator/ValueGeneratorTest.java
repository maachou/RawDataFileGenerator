package com.mehmaa.tools.rawdatafilegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValueGeneratorTest {

    private Field aField;

    @Before
    public void setUp() throws Exception {
	aField = new Field();
	aField.setLength(6);
    }

    @Test
    public void getYesNoDomainValueTest() {
	String result = ValueGenerator.getYesNoDomainValue(aField);
	Assert.assertTrue(result.equals("YES   ") || result.equals("NO    "));
    }

    @Test
    public void getGenderDomainValueTest() {
	String result = ValueGenerator.getGenderDomainValue(aField);
	Assert.assertTrue(result.equals("Male  ") || result.equals("Female"));
    }
}
