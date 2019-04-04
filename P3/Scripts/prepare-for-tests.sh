#!/bin/bash
# cd $(dirname $0)

if [ $1 == "restart" ];
then
	./restart.sh
	../limpiar-y-desplegar.sh
fi

if [ $1 == "stop" ];
then
	./stop.sh
	../limpiar-y-desplegar-base.sh
fi