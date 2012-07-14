package com.mehmaa.tools.rawdatafilegenerator;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 * Class responsible for generating values of diferent kinds
 * 
 * @author mehdimaachou
 * 
 */
public class ValueGenerator {

    /**
     * Get a string value with alphabetic, numeric or alphanumeric value
     * 
     * @param length
     * @param alpha
     * @param num
     * @return
     */
    public static String getStringValue(int length, boolean alpha, boolean num) {
	return RandomStringUtils.random(length, alpha, num);
    }

    /**
     * Getting a 'YES' or 'NO' string value
     * 
     * @param field
     * @return
     */
    public static String getYesNoDomainValue(Field field) {
	String domainValue = RandomUtils.nextBoolean() ? "YES" : "NO";
	while (domainValue.length() < field.getLength()) {
	    domainValue += " ";
	}
	return domainValue;
    }

    /**
     * Getting a 'Male' or 'Female' string value
     * 
     * @param field
     * @return
     */
    public static String getGenderDomainValue(Field field) {
	String domainValue = RandomUtils.nextBoolean() ? "Male" : "Female";
	while (domainValue.length() < field.getLength()) {
	    domainValue += " ";
	}
	return domainValue;
    }
}