#WSO2 Integration Bus

This is a temporary repo for Integration Bus (Next Generation of WSO2 ESB) work.


Building the Product
--------------------

Build the master branch of this repository.
Can be built only from JDK 1.8.


Running Samples
---------------

<b>- Sequence Diagram based DSL (External DSL) Samples</b>

Sample Integration Flow configurations are available at samples/SequenceDiagramDSLSamples directory.

Configuration can be deployed to server by dropping the file to <CARBON_HOME>/deployment/integration-flows/ directory.


<b>- Java DSL (Internal DSL) Samples</b>

There are two types of Java DSLs available (still WIP). We have a sample for each of these types.
Samples are available at samples/JavaDSLSamples directory.

A sample can be built using maven and the outcome is an osgi bundle which need to be deployed to 
 <CARBON_HOME>/osgi/dropins directory

