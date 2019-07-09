#!/usr/bin/env bash
java -javaagent:./tmp/jmx_prometheus_javaagent.jar=8085:./tmp/jmx_exporter.yaml -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1234 -jar target/spring-boot-soap-service-0.0.1-SNAPSHOT.jar
