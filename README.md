# xValid

xValid (short from XML Validator) is a simple CLI utility for validating
XML documents against XML Schema 1.1.

**Note:** xValid is just convenient  CLI wrapper around
[Xerces2](http://xerces.apache.org/xerces2-j/).
For production use it is recommended to use
[Xerces2](http://xerces.apache.org/xerces2-j/) directly.

## Installation 

Download and unpack the [latest release][latest release].

xValid requires Java version 21 or newer installed.

## Usage

    Usage: xvalid SCHEMA DOCUMENT
    Validate DOCUMENT against SCHEMA using XML Schema 1.1 and report the result.

Windows:

    .\bin\xvalid.bat schema.xsd document.xml
    
macOS, Linux and other Unix like systems:

    ./bin/xvalid schema.xsd document.xml

## Third Party Libraries

This software uses third party libraries.
For more information read the [NOTICE][notice] file.

## License

Copyright (c) Plamen Totev.

Licensed under the [MIT license][license].

  [latest release]: https://github.com/plamentotev/xvalid/releases/latest
  [license]: LICENSE
  [notice]: NOTICE
