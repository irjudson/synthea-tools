Embedded Synthea Example
========================

This repo is a working example of [embedding](https://github.com/synthetichealth/synthea/wiki/Embedding) [Synthea](https://github.com/synthetichealth/synthea).

Here's how to reproduce a working embedded application:

1. clone this repo (it will land in synthea-tools)
3. cd synthea-tools
3. clone the synthea repo (it will land in synthea-tools/synthea)
4. cd synthea
5. ./gradlew build check test
6. ./gradelw publishToMavenLocal
7. cd ../synthea-file
8. mvn clean package
9. java -jar .\target\synthea-file-1.0-SNAPSHOT-jar-with-dependencies.jar

* Note: Building synthea and installing it locally ensures you are using the same version/level of java for both synthea and synthea-file, this will likely not be required in the future but works around a reported issue for synthea (https://github.com/synthetichealth/synthea/issues/658)