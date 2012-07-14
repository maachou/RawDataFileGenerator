package com.mehmaa.tools.rawdatafilegenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

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

    public enum FIELD_TYPES {
	NUM, ALPHA, ALPHANUM, DOMAIN_YES_NO, DOMAIN_GENDER
    }

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
	LOG.debug("Cheking WellFormed-ness...");
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
	LOG.debug("XSD validation of the input file...");
	boolean result = false;
	try {
	    SAXParserFactory factory = SAXParserFactory.newInstance();
	    factory.setValidating(false);
	    factory.setNamespaceAware(true);

	    SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

	    InputStream dd = this.getClass().getResourceAsStream("/schema.xsd");
	    factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource(dd) }));

	    SAXParser parser = factory.newSAXParser();

	    XMLReader reader = parser.getXMLReader();
	    reader.setErrorHandler(new XmlErrorHandler());
	    reader.parse(new InputSource(this.specFilePath));
	    result = true;
	} catch (SAXException e) {
	    e.printStackTrace();
	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	} catch (IOException e) {
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
	LOG.debug("Converting the XML file to the equivalent Java Model..");
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
	try {
	    File file = new File("TestData.data");
	    FileWriter writer = new FileWriter(file);
	    StringBuilder rowBuilder = new StringBuilder();
	    for (int i = 0; i < model.getTotalrecords(); i++) {
		for (Field field : model.getFields()) {
		    switch (FIELD_TYPES.valueOf(field.getType())) {
		    case NUM:
			rowBuilder.append(ValueGenerator.getStringValue(field.getLength(), false, true));
			break;
		    case ALPHA:
			rowBuilder.append(ValueGenerator.getStringValue(field.getLength(), true, false));
			break;
		    case ALPHANUM:
			rowBuilder.append(ValueGenerator.getStringValue(field.getLength(), true, true));
			break;
		    case DOMAIN_YES_NO:
			rowBuilder.append(ValueGenerator.getYesNoDomainValue(field));
			break;
		    case DOMAIN_GENDER:
			rowBuilder.append(ValueGenerator.getGenderDomainValue(field));
			break;
		    default:
			break;
		    }
		}
		writer.write(rowBuilder.append("\n").toString());
		rowBuilder.setLength(0);
	    }
	    writer.flush();
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
