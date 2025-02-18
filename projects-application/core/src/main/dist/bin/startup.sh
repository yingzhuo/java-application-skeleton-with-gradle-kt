#!/bin/sh
########################################################################################################################
# 本文件由红熊运维工程师维护
# 如有需要可修改和添加若干Java虚拟机参数
########################################################################################################################

export JAVA="${JAVA_HOME}/bin/java"

JAVA_OPT="${JAVA_OPT} -server -Xmixed"
JAVA_OPT="${JAVA_OPT} -XX:-PrintCommandLineFlags -XX:-PrintFlagsInitial -XX:-PrintFlagsFinal"
JAVA_OPT="${JAVA_OPT} -XX:ThreadStackSize=1m"
JAVA_OPT="${JAVA_OPT} -XX:InitialHeapSize=4g -XX:MinHeapSize=4g -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+UseTLAB"
JAVA_OPT="${JAVA_OPT} -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -XX:+UseCompressedOops"
JAVA_OPT="${JAVA_OPT} -XX:MaxDirectMemorySize=1g"
JAVA_OPT="${JAVA_OPT} -XX:+UseG1GC -XX:MaxGCPauseMillis=300"
JAVA_OPT="${JAVA_OPT} -XX:+UsePerfData"

JAVA_OPT_EXT="${JAVA_OPT_EXT} -Djava.security.egd=file:/dev/./urandom"
JAVA_OPT_EXT="${JAVA_OPT_EXT} -Dloader.system=false"
JAVA_OPT_EXT="${JAVA_OPT_EXT} -Dloader.home=${APP_HOME}"
JAVA_OPT_EXT="${JAVA_OPT_EXT} -Dloader.path=${APP_HOME}/lib,${APP_HOME}/config"
JAVA_OPT_EXT="${JAVA_OPT_EXT} -Dspring.config.name=application"
JAVA_OPT_EXT="${JAVA_OPT_EXT} -Dspring.config.additional-location=file:${APP_HOME}/config/"
JAVA_OPT_EXT="${JAVA_OPT_EXT} -Dlogging.config=${APP_HOME}/config/logback.xml"

${JAVA} \
  ${JAVA_OPT} \
  ${JAVA_OPT_EXT} \
  -cp ${APP_HOME}/lib/daemon.jar \
  org.springframework.boot.loader.launch.PropertiesLauncher \
  --spring.profiles.active=prod
