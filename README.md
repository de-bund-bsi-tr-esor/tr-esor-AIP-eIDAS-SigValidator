![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

# XAIPValidator
The XAIPValidator is a tool designed for validation of an XML formatted Archival Information Package (XAIP). Using a modular structure and plugin mechanism, the validator is validating an XAIP by using those modules which can vary by their implementation.

Validation will be done step by step in following order:

1. Dispatcher loading modules
2. Calling SyntaxModule
3. Calling SignatureFinderModule
4. Calling SignatureVerificationModule
5. Calling ProtocolAssemblerModule

![Components](overview_components.jpg "Components")

## Quickstart

```
git clone https://github.com/de-bund-bsi-tr-esor/tr-esor-AIP-eIDAS-SigValidator.git

cd tr-esor-AIP-eIDAS-SigValidator

mvn clean package

java -jar xaip-validator-cli/target/xaip-validator-cli.jar -i ~/sample.xaip -Mvalidator.schemaDir=default-syntax-validator/src/main/resources/definition

# CLI
# Mac/Linux/Windows
java -jar xaip-validator-cli/target/xaip-validator-cli.jar -i ~/sample.xaip -Mvalidator.schemaDir=default-syntax-validator/src/main/resources/definitions


# SOAP Server
# Mac/Linux
java -cp "xaip-validator-soap/target/xaip-validator-soap-1.0.7-2.jar:target/dependency/*" de.bund.bsi.tresor.xaip.validator.soap.Server -Mvalidator.schemaDir=default-syntax-validator/src/main/resources/definition -Mverifier.wsdlUrl="https://host:port/VerificationService/S4?wsdl"

# Windows
java -cp "xaip-validator-soap/target/xaip-validator-soap-1.0.7-2.jar;target/dependency/*" de.bund.bsi.tresor.xaip.validator.soap.Server -Mvalidator.schemaDir=default-syntax-validator/src/main/resources/definition -Mverifier.wsdlUrl="https://host:port/VerificationService/S4?wsdl"
```

## Prerequisites
- java 11 (>= 11.0.10)

	- for executing the CLI jar
- maven

	- to build the project and loading dependencies
	
- git-client

	- to pull this project, alternatively the project can be downloaded manually via git web

## Installation
- **Step 1:** Install Java 11

	- OpenJDK: https://openjdk.java.net/install/

- **Step 2:** Install Maven

	- Apache Maven Installation Guide: http://maven.apache.org/install.html

- **Step 3:** Install GitHub

	- GitHub installation guide: https://github.com/git-guides/install-git
	
	- GitHub GUI clients: https://git-scm.com/downloads/guis

- **Step 4:** Clone the Project

	- GitHub documentation: https://github.com/git-guides/git-clone

- **Step 5:** Build the Project

	- open up a terminal on the project root node
	
	- call `mvn clean package`

- **Optional:** Create javadoc html

	- call `mvn javadoc:aggregate -Ddoclint=none` to create the javadoc on the root node
	
	- the javadoc can be open up via `tr-esor-AIP-eIDAS-SigValidator/target/site/apidocs/index.html` (open in browser)
	
- **Use the Validator:**

	- example call:
	
```
java -jar xaip-validator-cli/target/xaip-validator-cli.jar -i ~/Dokumente/sample.xaip -Mvalidator.schemaDir=/tmp/tr-esor-AIP-eIDAS-SigValidator/default-syntax-validator/src/main/resources/definitions
```

### Quick Example

```
git clone https://github.com/de-bund-bsi-tr-esor/tr-esor-AIP-eIDAS-SigValidator.git

cd tr-esor-AIP-eIDAS-SigValidator

mvn clean package
mvn javadoc:aggregate -Ddoclint=none

java -jar xaip-validator-cli/target/xaip-validator-cli.jar -i ~/sample.xaip -Mvalidator.schemaDir=default-syntax-validator/src/main/resources/definition


```

### Complete Example

In this example, the project was checked out under `/tmp` so the root node of the project is `/tmp/tr-esor-AIP-eIDAS-SigValidator`:

```
$ pwd
/tmp/tr-esor-AIP-eIDAS-SigValidator
```

Build the project using maven:
```
$ mvn clean package
```

A successful build should print a *`BUILD SUCCESS`* message. When following message is being printed, your shell is not using `java 11+`: *`invalid target release: 11`*. 


The tool can be found in the target directory of the *`xaip-validator-cli`* submodule:
```
$ ls xaip-validator-cli/target/
archive-tmp           checkstyle-checker.xml  checkstyle-rules.xml         classes            generated-test-sources  maven-status  xaip-validator-cli-1.0.7-2.jar
checkstyle-cachefile  checkstyle-result.xml   checkstyle-suppressions.xml  generated-sources  maven-archiver          test-classes  xaip-validator-cli.jar
```

The `jar` which can be executed does not contain version information in its name (`xaip-validator-cli.jar`).
Reason for this are the required runtime dependencies. Those are being added to the versioned `jar` resulting in the `xaip-validator-cli.jar`.

When executing, the first module (syntax validator) requires schema information for the xaip validation.
If no specific profiles are being used, following directory can be passed to the tool to provide those schemas:

`tr-esor-AIP-eIDAS-SigValidator/default-syntax-validator/src/main/resources/definitions/`

A handy format of the javadoc can be created by calling `mvn javadoc:aggregate -Ddoclint=none` on the root project.
The javadoc will be available under `tr-esor-AIP-eIDAS-SigValidator/target/site/apidocs/index.html` which should be opened using an internet browser.

**Important Notes**

Without any additional configuration the xaip validator is using the default module implementations.
For more informations take a look into the module section where the module funktionality is being further explained.

Any known issues about the validator are being explained at the bottom of this page.


## Usage
### CLI

**Description:** The cli version of the XAIPValidator is being used for validation of XAIP's via the command line.

**Usage:** `java -jar xaip-validator.jar [OPTION [ARG]*]*`

**Options:**

```
-M=<moduleConfig>
    Passing a single module configuration property to the validator.
    The property and requirements for any module configuration properties are defined by the implementing module.

    Example: -Mverifier.wsdlUrl=http://localhost:8080/s4?wsdl

    This example is using the property config verifier.wsdlUrl which is a required config property 
    of the DefaultVerifierModule. Instead of passing multiple module properties as an argument, a config file
    which contains the module properties can be passed instead.
    For more informations see –c, --config <file>

-c, --config <file>
    Passing a configuration file in form of a property file to the validator.
    This file can contain any module configuration property. The configuration file is being passed to every
    module so they can scan the content and retrieve any configuration properties they define. 

    The handling is just the same as passing all configuration properties via a separate command line argument.
    This argument can also be used together with single command line module property arguments.

    Example: -c config.properties

    Content of config.properties:
      validator.schemaDir=/tmp/xaip/definition
      verifier.wsdlUrl=http://localhost:8080/s4?wsdl

-i, --in, --input <file>
    Passing a <file> as a source for the xaip validation.
    Omitting this argument will take the standard <inpuStream> for the validation.

    Example: -i /tmp/sample.xaip
	
-o, --out, --output <file>
    Defining a definition for the validation result.
    Omitting this argument will write the result to the standard <outputStream> instead.

    Example: -o /tmp/report.xml

-v, --verify
    This flags enables the signature verification which is being executed by the [SignatureVerifierModule].
    Omitting this flag will only execute the syntax validation.
    
-d, --debug
    Flag to enable debug output for a better analysis of the validator behaviour.
    This output can contain stacktraces or other kinds of errors even when everything works fine.

-l, --log <file>
    Since this tool is not only creating a report but also log output this argument can be used
    to separate the log output of the validator into a dedicated document.

    Example: -l validator.log

-h, --help
	Printing the usage of the validator.
```

### Server

**Description:** The server version of the XAIPValidator is being used to provide a soap service implementing the verify function of the eCard api. This api can be used to send a verifyRequest containing an XAIP which will be validated in the following steps.

**Usage:** `java -cp "target/xaip-validator-soap-1.0.6-SNAPSHOT.jar:target/dependency/*" de.bund.bsi.tresor.xaip.validator.soap.Server [OPTION [ARG]*]*`

**Options:**

```	
-M=<moduleConfig>
    Passing a single module configuration property to the validator.
    The property and requirements for any module configuration properties are defined by the implementing module.

    Example: -Mverifier.wsdlUrl=http://localhost:8080/s4?wsdl

    This example is using the property config verifier.wsdlUrl which is a required config property 
    of the DefaultVerifierModule. Instead of passing multiple module properties as an argument, a config file
    which contains the module properties can be passed instead.
    For more informations see –c, --config <file>

-c, --config <file>
    Passing a configuration file in form of a property file to the validator.
    This file can contain any module configuration property. The configuration file is being passed to every
    module so they can scan the content and retrieve any configuration properties they define. 

    The handling is just the same as passing all configuration properties via a separate command line argument.
    This argument can also be used together with single command line module property arguments.

    Example: -c config.properties

    Content of config.properties:
      validator.schemaDir=/tmp/xaip/definition
      verifier.wsdlUrl=http://localhost:8080/s4?wsdl

-p, --port <port>
	Port the server should be published to, 8080 by default

-P, --protocol <protocol>
	Protocol to be used, `http` by default

-H, --host <hostname>
	Hostname the server is published to

--path <path>
	Custom path the service should be used, `/xaip-validate` by default
    
-d, --debug
    Flag to enable debug output for a better analysis of the validator behaviour.
    This output can contain stacktraces or other kinds of errors even when everything works fine.

-h, --help
	Printing the usage of the validator.
```

## Modules

### Default Validator

**Description:** Validating the XAIP using the provided schema files (XSD's)

**Configurations:**

| ConfigName            | Example          | Description                                       |
|-----------------------|------------------|---------------------------------------------------|
| *validator.schemaDir  | /tmp/xaip-schema | schema directory containing all xaip schema files |

\* - required configuration

### Default Finder

**Description:** Finding any signature in the XAIP which can be in the data- or credentialSection.

**Configurations:** NONE

### Default Verifier

**Description:** Sending the found signature objects to a configured verification service and retrieving the response

**Configurations:**

| ConfigName        | Example                                                   | Description                          |
|-------------------|-----------------------------------------------------------|--------------------------------------|
| *verifier.wsdlUrl | https://host:port/VerificationService/S4?wsdl             | url of the verification service wsdl |
|  verifier.user    | umsysadmin                                                | uid for the token creation           |
|  verifier.pass    | someSecret                                                | password of the user                 |
|  verifier.umUrl   | https://protectr.procilon.test/UserManager/v1/login       | loginUrl of the procilon userManager |
|  verifier.idpUrl  | https://protectr.procilon.test/idp/profile/SAML2/SOAP/ECP | idpUrl of the procilon idp           |

\* - required configuration


### Default Assembler

**Description:** Merging all results into one report

**Configurations:** NONE

## Known Issues and Limitations

The following limitations apply:

- The XAIPValidator has a strong dependency to the external signature validation service. The current version of the service is not able to validate XML signatures properly. Therefore the validation of XAIPs containing XML signatures will lead to validation reports with unsupported signature formats.
- The processing of XAdES-compliant signature objects is currently not fully supported. This issue will be fixed in one of the next SNAPSHOT releases
- Information for `TransformInfoType` is currently not generated due to inconsistencies in the verification report scheme
- Extensions are not evaluated due to their dependency to specific profiles
- The content of Metadata sections is not evaluated with the exception of their well-formedness

The following issues are known:
- **[XVAL-1]** When using the paramter `-o` the provided argument has to be a file which is not in the current directory

## Test Environment ##

- Use the following URL for the validation service: https://staging.protectr.de/VerificationService/S4?wsdl
- Testmaterial is supplied separately together with sample XAIP-files and a shell script for test execution

## License ##

This software is underlying the rules of the following license: Apache License Version 2.0, January 2004.\
The software was created by procilon IT-Logistics GmbH on behalf of the Federal Office for Information Security.

## Contact ##

**Federal Office for Information Security (BSI)**\
Godesberger Allee 185-189\
53175 Bonn, Germany\
phone: +49 228 99 9582-0\
fax: +49 228 99 9582-5400\
e-mail: bsi@bsi.bund.de

and

**procilon IT-Logistics GmbH**\
Leipziger Straße 110\
04425 Taucha bei Leipzig, Germany\
phone: +49 34298 4878 31\
e-mail: anfrage@procilon.de
