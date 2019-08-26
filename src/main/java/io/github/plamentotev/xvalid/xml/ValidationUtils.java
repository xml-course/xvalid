package io.github.plamentotev.xvalid.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Utility class for working with schemas and validators.
 *
 * <p> Unless otherwise noted, passing a {@code null}
 * argument to a constructor or method in this class
 * will cause a {@link NullPointerException} to be thrown.
 */
public final class ValidationUtils {

    private static final String XML_SCHEMA_1_1 =
        "http://www.w3.org/XML/XMLSchema/v1.1";

    private  ValidationUtils() {
        // This is utility class so no instances allowed
    }

    /**
     * Initializes XML Schema 1.1 {@link Schema} instance from
     * a file.
     *
     * @param schema file with the schema
     * @param errorHandler {@link ErrorHandler} to receive the errors
     *                     that occur during the schema parsing
     *
     * @return initialized schema
     *
     * @throws SAXException if error occurs while parsing the schema
     */
    public static Schema initXmlSchema(File schema,
                                       ErrorHandler errorHandler)
        throws SAXException
    {
        Objects.requireNonNull(schema, "schema must not be null");
        Objects.requireNonNull(errorHandler, "errorHandler must not be null");

        var schemaFactory = SchemaFactory.newInstance(XML_SCHEMA_1_1);
        schemaFactory.setErrorHandler(errorHandler);

        return schemaFactory.newSchema(schema);
    }

    /**
     * Validates a XML document against given schema.
     *
     * @param document XML document to validate
     * @param schema schema used to validate {@code document}
     * @param errorHandler {@code ErrorHandler} to receive the errors
     *                     that occur during the validation
     *
     * @throws IOException if {@link IOException} is thrown while
     *                     reading {@code document}
     * @throws SAXException if error occurs while validating the document
     */
    public static void validate(File document,
                                Schema schema,
                                ErrorHandler errorHandler)
        throws IOException, SAXException
    {
        Objects.requireNonNull(document, "document must not be null");
        Objects.requireNonNull(schema, "schema must not be null");
        Objects.requireNonNull(errorHandler, "errorHandler must not be null");

        var validator = schema.newValidator();
        validator.setErrorHandler(errorHandler);

        validator.validate(new StreamSource(document));
    }

}
