#!/bin/sh
# Created on Fri Jun 21 20:01:05 -03 2019
java -Xmx256m -Xms64m -jar /app/service/bin/spag-contabil-job-0.0.1.jar --spring.config.name=spag-contabil-job-config --spring.config.location=file:/app/service/config/
