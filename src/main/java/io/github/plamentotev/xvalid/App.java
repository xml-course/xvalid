package io.github.plamentotev.xvalid;

import io.github.plamentotev.xvalid.xml.ReportingErrorHandler;
import io.github.plamentotev.xvalid.xml.ValidationUtils;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();

            return;
        }

        var schemaFilePath = args[0];
        var documentFilePath = args[1];
        try {
            doValidate(schemaFilePath, documentFilePath);
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void doValidate(String schemaFilePath,
                                   String documentFilePath)
        throws SAXException, IOException
    {
        // Initialize the schema that is going to be used
        // to validate the XML document

        var schemaFile = new File(schemaFilePath);
        var schemaFileName = schemaFile.getName();
        var schemaErrorHandler = new ReportingErrorHandler(System.err,
            schemaFileName);

        var schema = ValidationUtils.initXmlSchema(schemaFile,
            schemaErrorHandler);

        // If the schema contains errors there is no pint
        // trying to validate the document
        if (schemaErrorHandler.isErrorFound()) {
            System.out.println("The XML Schema contains errors. " +
                "Aborting validation.");

            return;
        }

        // Validate the XML document

        var documentFile = new File(documentFilePath);
        var documentFileName = documentFile.getName();
        var documentErrorHandler = new ReportingErrorHandler(System.err,
                documentFileName);

        ValidationUtils.validate(documentFile, schema, documentErrorHandler);

        // Report the validation result
        if (documentErrorHandler.isErrorFound()) {
            System.out.println("Validation errors found.");
        } else {
            System.out.println("Validation passed successfully.");
        }
    }

    private static void printUsage() {
        System.out.println("Usage: xvalid SCHEMA DOCUMENT");
        System.out.println("Validate DOCUMENT against SCHEMA using " +
            "XML Schema 1.1 and report the result.");
        System.out.println();
        System.out.println("Example:");
        System.out.println("  $ xvalid maven-4.0.0.xsd pom.xml");
    }

}
