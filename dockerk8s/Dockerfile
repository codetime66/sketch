FROM jre8base:01
#
ENV APPBASE /app/service
ENV BINDIR tmp
ENV CFGDIR tmp
#
ARG LOGDIR
ARG BINFILE 
ARG CFGFILE
#
RUN mkdir -p ${APPBASE}/bin
RUN mkdir -p ${APPBASE}/config
RUN mkdir -p ${LOGDIR}
#
COPY ${BINDIR}/${BINFILE} ${APPBASE}/bin/${BINFILE}
COPY ${CFGDIR}/${CFGFILE} ${APPBASE}/config/${CFGFILE}
COPY app-entrypoint.sh ${APPBASE}/bin/app-entrypoint.sh
#
WORKDIR ${APPBASE}/bin
EXPOSE 9092
VOLUME ${LOGDIR}
CMD ["sh","app-entrypoint.sh"]
