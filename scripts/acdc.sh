#!/bin/bash
# make sure you install anguler-cli (after installing npm) using:
# sudo npm install --unsafe-perm  -g @angular/cli

GiTHuB=https://github.com/agayaga/rabbitlambda

GITDIR=$(mktemp -d)
echo GITDIR=${GITDIR}
pushd ${GITDIR}
  echo "PwD=${PWD}"
  git clone -b rabbit-web-app ${GiTHuB}
  echo "PwD=${PWD}"
  cd rabbitlambda/rabbit-web-v2
  npm install
  ng build
popd




