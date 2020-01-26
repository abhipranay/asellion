#!/usr/bin/env bash

if [ -x "$(command -v docker)" ]; then
  echo "Found docker"
else
  echo "docker not installed.."
  exit 1
fi

if [ -x "$(command -v docker-compose)" ]; then
  echo "Found docker-compose"
else
  echo "docker-compose not installed.."
  exit 1
fi

if [ -x "$(command -v mvn)" ]; then
  echo "Found maven"
else
  echo "maven not installed.."
  exit 1
fi

mvn jib:dockerBuild -Dimage=asellion/app

echo "Starting services..."
docker-compose stop
docker-compose up -d

set -eu
declare -r HOST="localhost:8080/api/v1"

wait-for-url() {
  echo "Testing $1"
  until [[ $(curl -L -s -o /dev/null -w "%{http_code}" $1) =~ 2[0-9][0-9] ]]; do
    printf '.'
    sleep 5
  done
  echo "OK!"
}
wait-for-url http://${HOST}
exit 0
