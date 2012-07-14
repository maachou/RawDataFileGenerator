package com.mehmaa.tools.rawdatafilegenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Class tool that contains various method operating on the input file : cheking
 * wellformedness, xsd validatoion, xmlToObject and data rows generation.
 * 
 * @author mehdimaachou
 * 
 */
public class DataGeneratorTool {

    static final Logger LOG = LoggerFactory.getLogger(DataGeneratorTool.class);
    private final String specFilePath;

    /**
     * Constructor
     * 
     * @param filePath
     */
    public DataGeneratorTool(final String filePath) {
	this.specFilePath = filePath;
    }

    /**
     * Checking spec file wellformed-ness
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public boolean checkWellFormedness() {
	boolean result = false;
	SAXParserFactory factory = SAXParserFactory.newInstance();
	factory.setValidating(false);
	factory.setNamespaceAware(true);
	SAXParser parser;
	try {
	    parser = factory.newSAXParser();
	    XMLReader reader = parser.getXMLReader();
	    reader.setErrorHandler(new XmlErrorHandler());
	    reader.parse(new InputSource(this.specFilePath));
	    result = true;
	} catch (ParserConfigurationException e) {
	    System.err.println("Error: ParserConfigurationException.");
	    e.printStackTrace();
	} catch (SAXException e) {
	    System.err.println("Error: SAXException.");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.err.println("Error: IOException.");
	    e.printStackTrace();
	}
	return result;
    }

    /**
     * XSD Validation
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public boolean validateAgaintXsd() {
	boolean result = false;
	try {
	    SAXParserFactory factory = SAXParserFactory.newInstance();
	    factory.setValidating(true);
	    factory.setNamespaceAware(true);
	    SAXParser parser = factory.newSAXParser();
	    parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
		    "http://www.w3.org/2001/XMLSchema");
	    XMLReader reader = parser.getXMLReader();
	    reader.setErrorHandler(new XmlErrorHandler());
	    reader.parse(new InputSource(this.specFilePath));
	    result = true;
	} catch (ParserConfigurationException e) {
	    System.err.println("Error: ParserConfigurationException.");
	    e.printStackTrace();
	} catch (SAXException e) {
	    System.err.println("Error: SAXException.");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.err.println("Error: IOException.");
	    e.printStackTrace();
	}
	return result;
    }

    /**
     * Convert an XML file to a Data object
     * 
     * @return
     */
    public Data getSpecModel() {
	Data result = null;
	try {
	    XStream xstream = new XStream(new DomDriver());
	    xstream.alias("data", Data.class);
	    xstream.alias("field", Field.class);
	    Data specModel = new Data();
	    FileInputStream fileInputStream = new FileInputStream(new File(this.specFilePath));
	    xstream.fromXML(fileInputStream, specModel);
	    fileInputStream.close();
	    result = specModel;
	} catch (IOException e) {
	    System.err.println("Error: IOException.");
	    e.printStackTrace();
	}
	return result;
    }

    /**
     * Test Data generation
     */
    public void generateData(Data model) {
	LOG.debug("Generating Data...(TODO)");
    }

}
