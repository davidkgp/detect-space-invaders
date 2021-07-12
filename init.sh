#!/bin/bash

mvn clean install

FAULT_TOLERANCE_PERCENTAGE=10 INPUT_ROOT=$(pwd)/input/ java -jar ./target/detect-space-invaders-1.0-SNAPSHOT-jar-with-dependencies.jar
