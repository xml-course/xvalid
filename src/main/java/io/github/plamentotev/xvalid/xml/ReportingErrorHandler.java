package io.github.plamentotev.xvalid.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import java.io.PrintStream;
import java.util.Objects;

/// A simple implementation of [ErrorHandler] that
/// reports all errors to given [PrintStream].
///
/// A call to [#error(SAXParseException)] or
/// [#fatalError(SAXParseException)] will cause
/// [#isErrorFound()] to return `true`.
/// Calls to [#warning(SAXParseException)]
/// does not have effect on the [#isErrorFound()]
/// return value.
///
/// Unless otherwise noted, passing a `null`
/// argument to a constructor or method in this class
/// will cause a [NullPointerException] to be thrown.
public class ReportingErrorHandler implements ErrorHandler {

    private final PrintStream output;

    private final String source;

    private boolean errorFound = false;

    /// Constructs new [ReportingErrorHandler] instance.
    ///
    /// @param output all errors and errors are going to be reported
    ///               to the provided [PrintStream]
    /// @param source the name of the source that is being processed.
    ///               It is used in the error messages
    public ReportingErrorHandler(PrintStream output, String source) {
        this.source = source;
        Objects.requireNonNull(output, "output must not be null");

        this.output = output;
    }

    /// Returns whether an error was reported to this handler.
    ///
    /// @return `true` if an error was reported,
    ///         `false` otherwise
    public boolean isErrorFound() {
        return errorFound;
    }

    @Override
    public void warning(SAXParseException exception) {
        Objects.requireNonNull(exception, "exception must not be null");

        reportError("Warning", exception);
    }

    @Override
    public void error(SAXParseException exception) {
        Objects.requireNonNull(exception, "exception must not be null");

        handleErrorFound();

        reportError("Error", exception);
    }

    @Override
    public void fatalError(SAXParseException exception) {
        Objects.requireNonNull(exception, "exception must not be null");

        handleErrorFound();

        reportError("Fatal", exception);
    }

    private void handleErrorFound() {
        errorFound = true;
    }

    private void reportError(String level, SAXParseException exception) {
        var line = exception.getLineNumber();
        var column = exception.getColumnNumber();

        var message = line > -1 && column > -1
            ? String.format("%s: %s(%d:%d): %s",
                level, source, line, column, exception.getMessage())
            : String.format("%s: %s: %s",
                level, source, exception.getMessage());

        output.println(message);
    }

}
