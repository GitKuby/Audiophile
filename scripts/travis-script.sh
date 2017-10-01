#!/bin/bash -e

cd $(dirname $0)
cd ..

D=$PWD

# cd hsqldb-scripts
# ./server.sh &
# SRVPID=$!
# sleep 1s


# cd Audiophile
mvn test


# kill $SRVPID
