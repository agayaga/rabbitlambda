#!/bin/bash
# make sure you install anguler-cli (after installing npm) using:
# sudo npm install --unsafe-perm  -g @angular/cli

GiTHuB=https://github.com/agayaga/rabbitlambda

GITDIR=$(mktemp -d)
pushd ${GITDIR}
  git clone -b rabbit-web-app ${GiTHuB}
  cd rabbitlambda/bbit-web-v2/
  npm install
  ng build
popd





