#!/bin/bash
#prepare
source ./config.sh
source ./create_entrypoint.sh
mkdir tmp
cp ${APP_PKG_DIR}${APP_PKG_NAME} tmp/${APP_PKG_NAME}
cp ${APP_CFG_DIR}${APP_CFG_NAME} tmp/${APP_CFG_NAME}
#build
docker build --build-arg LOGDIR=${APP_LOG_DIR} --build-arg BINFILE=${APP_PKG_NAME} --build-arg CFGFILE=${APP_CFG_NAME} -t ${APP_IMG_NAME} .
#cleanup
rm -rf tmp
