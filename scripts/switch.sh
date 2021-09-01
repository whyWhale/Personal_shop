#!/bin/bash/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
  IDLE_PORT=$(find_idle_port)

  echo "> 전환 할 PORT : $IDLE_PORT"
  echo "> PORT 전환 @@@@@@@@@@@@"
  echo "set /$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

  echo "> 엔진엑스 ReLoad~ "
  sudo service nginx reload

}