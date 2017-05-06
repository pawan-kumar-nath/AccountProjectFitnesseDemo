#!/bin/sh
# ***********************************************************************
# Command line script to run Account DW app
#
# This tool requires JAVA_HOME/bin directory to be present in PATH
# environment variable.
#
# All the environment variables mentioned below are required and tool
# will fail without setting them.
# ***********************************************************************

dbHost={host}:{port}:{sid}
export dbHost
dbUser={user}
export dbUser
dbPassword={password}
export dbPassword
defaultLogLevel=INFO
export defaultLogLevel

java -Xmx2048m -Xms512m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4005 -jar target/AccountProjectFitnesseDemo-0.0.1-SNAPSHOT.jar server account-service.yml
