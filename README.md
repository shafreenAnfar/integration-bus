#WSO2 Integration Bus

This is a tempary repo for Integration Bus (Next Generation of WSO2 ESB) work.

Sample Java DSL is available at sample/SampleDSL which can be built using maven.
After building the sample an osgi bundle is getting generated at target which we can copy to the <CARBON_HOME>/osgi/dropins directory


Steps for Building
------------------

Build
1. https://github.com/isururanawaka/carbon-messaging branch : dynamicListenerSupport

2. https://github.com/isururanawaka/carbon-transports branch : dynamicportbinding

3. Build master branch of this