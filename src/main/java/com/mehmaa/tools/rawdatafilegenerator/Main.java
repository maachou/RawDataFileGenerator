package com.mehmaa.tools.rawdatafilegenerator;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mehmaa.tools.rawdatafilegenerator.exceptions.UnSupportedFileFormat;

public class Main {
    static final Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * Main executable method used to demonstrate Apache Commons CLI.
     * 
     * @param commandLineArguments
     *            Commmand-line arguments.
     */
    public static void main(final String[] commandLineArguments) {
	LOG.debug("Processing...");
	if (commandLineArguments.length < 1) {
	    System.out.println("-- HELP --");
	    printHelp(constructGnuOptions(), 80, 5, 3, true, System.out);
	}
	useParser(commandLineArguments);
    }

    /**
     * Apply Apache Commons CLI GnuParser to command-line arguments.
     * 
     * @param commandLineArguments
     *            Command-line arguments to be processed with Gnu-style parser.
     */
    public static void useParser(final String[] commandLineArguments) {
	final CommandLineParser cmdLineGnuParser = new GnuParser();
	final Options gnuOptions = constructGnuOptions();
	CommandLine commandLine;
	try {
	    String specFilePath = null;
	    commandLine = cmdLineGnuParser.parse(gnuOptions, commandLineArguments);
	    if (commandLine.hasOption("s")) {
		specFilePath = commandLine.getOptionValue('s');
		File inputFile = new File(specFilePath);
		if (inputFile.exists() && specFilePath.toLowerCase().endsWith(".xml")) {
		    DataGeneratorTool generatorTool = new DataGeneratorTool(specFilePath);
		    if (generatorTool.checkWellFormedness() && generatorTool.validateAgaintXsd()) {
			Data model = generatorTool.getSpecModel();
			if (model != null) {
			    generatorTool.generateData(model);
			}
		    }
		} else {
		    throw new UnSupportedFileFormat();
		}
	    }

	} catch (ParseException parseException) {
	    System.err.println("Encountered exception while parsing the current command:\n"
		    + parseException.getMessage());
	} catch (UnSupportedFileFormat e) {
	    e.printStackTrace();
	}
    }

    /**
     * Construct and provide GNU-compatible Options.
     * 
     * @return Options expected from command-line of GNU form.
     */
    public static Options constructGnuOptions() {
	final Options gnuOptions = new Options();
	gnuOptions.addOption("s", true, "Specification file(xml)");
	return gnuOptions;
    }

    /**
     * Display command-line arguments without processing them in any further
     * way.
     * 
     * @param commandLineArguments
     *            Command-line arguments to be displayed.
     */
    public static void displayProvidedCommandLineArguments(final String[] commandLineArguments, final OutputStream out) {
	final StringBuffer buffer = new StringBuffer();
	for (final String argument : commandLineArguments) {
	    buffer.append(argument).append(" ");
	}
	try {
	    out.write((buffer.toString() + "\n").getBytes());
	} catch (IOException ioEx) {
	    System.err.println("WARNING: Exception encountered trying to write to OutputStream:\n" + ioEx.getMessage());
	    System.out.println(buffer.toString());
	}
    }

    /**
     * Write the provided number of blank lines to the provided OutputStream.
     * 
     * @param numberBlankLines
     *            Number of blank lines to write.
     * @param out
     *            OutputStream to which to write the blank lines.
     */
    public static void displayBlankLines(final int numberBlankLines, final OutputStream out) {
	try {
	    for (int i = 0; i < numberBlankLines; ++i) {
		out.write("\n".getBytes());
	    }
	} catch (IOException ioEx) {
	    for (int i = 0; i < numberBlankLines; ++i) {
		System.out.println();
	    }
	}
    }

    /**
     * Write "help" to the provided OutputStream.
     */
    public static void printHelp(final Options options, final int printedRowWidth, final int spacesBeforeOption,
	    final int spacesBeforeOptionDescription, final boolean displayUsage, final OutputStream out) {
	final String commandLineSyntax = "java -jar RawDataFileGenerator.one-jar.jar ";
	final PrintWriter writer = new PrintWriter(out);
	final HelpFormatter helpFormatter = new HelpFormatter();
	helpFormatter.printHelp(writer, printedRowWidth, commandLineSyntax, "", options, spacesBeforeOption,
		spacesBeforeOptionDescription, "", displayUsage);
	writer.flush();
    }

}
