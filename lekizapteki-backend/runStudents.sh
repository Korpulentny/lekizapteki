#!/usr/bin/env bash

export JAVA_HOME=/opt/jdk-11.0.2
mvn clean install
mvn spring-boot:run
