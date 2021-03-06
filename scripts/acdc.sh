#!/bin/bash
# make sure you install anguler-cli (after installing npm) using:
#     #                                 #     #         ######
#     #  ######  #       #        ####  #  #  #   ####  #     #  #       #####
#     #  #       #       #       #    # #  #  #  #    # #     #  #       #    #
#######  #####   #       #       #    # #  #  #  #    # ######   #       #    #
#     #  #       #       #       #    # #  #  #  #    # #   #    #       #    #
#     #  #       #       #       #    # #  #  #  #    # #    #   #       #    #
#     #  ######  ######  ######   ####   ## ##    ####  #     #  ######  #####

DIR=${HOME}/.aws

[[ ! -d ${DIR} ]] && mkdir ${DIR}
echo "[default]" > ${DIR}/credentials 
echo aws_access_key_id=${aws_access_key_id} >> ${DIR}/credentials 
echo aws_secret_access_key=${aws_secret_access_key} >> ${DIR}/credentials 
echo -e "[default]\nregion = us-east-2" > ${DIR}/config

npm install --unsafe-perm @angular/cli
GiTHuB=https://github.com/agayaga/rabbitlambda
GITDIR=$(mktemp -d)
echo GITDIR=${GITDIR}
pushd ${GITDIR}
  echo "PwD=${PWD}"
  git clone ${GiTHuB}
#  git clone -b rabbit-web-app ${GiTHuB}
  echo "PwD=${PWD}"
  cd rabbitlambda/rabbit-web-v2
  npm install
  node_modules/@angular/cli/bin/ng build
popd




