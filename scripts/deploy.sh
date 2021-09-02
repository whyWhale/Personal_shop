#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=springboot-whywhale2

echo "> build file copy"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 PID Check! "

CURRENT_PID=$(pgrep -fl springboot-whywhale2 | pgrep java | awk '{print $1}')

echo "> 현재 구동 중인 애플리케이션 PID = : $CURRENT_PID "

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없습니다. 그러므로그래서 종료하지 않습니다. "
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> new application deployment !"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR_NAME = $JAR_NAME"

echo "> $JAR_NAME 에 실행 권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행 !"

nohup java -jar \
              -Dspring.config.location=classpath:/application.yaml,/home/ec2-user/app/application-release.yaml \
              -Dspring.profiles.active=real\
              $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &


