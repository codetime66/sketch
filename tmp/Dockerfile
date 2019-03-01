FROM openjdk:8-jre-alpine
#
RUN apk add tzdata
RUN cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
RUN echo "America/Sao_Paulo" >  /etc/timezone
#
ENV APPBASE=/app/standalone/link-terminal-seller-job
ENV APPLOG=/app/log/standalone/link-terminal-seller-job
ENV BINLOC=./target
ENV BINNAME=link-terminal-seller-job-1.0-SNAPSHOT.jar
#
RUN mkdir -p ${APPBASE}
RUN mkdir -p ${APPLOG}
#
COPY application.properties ${APPBASE}/application.properties
COPY ${BINLOC}/${BINNAME} ${APPBASE}/${BINNAME}
COPY app-start.sh ${APPBASE}/app-start.sh
#
WORKDIR ${APPBASE}
EXPOSE 9001
VOLUME ${APPLOG}
CMD ["sh","app-start.sh"]

