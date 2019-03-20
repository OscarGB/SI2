#!/bin/bash
for i in P1-base P1-ws P1-ejb-servidor-remoto P1-ejb-cliente-remoto; do
	cd $i
	ant replegar; ant delete-pool-local
	cd -
done

for i in P1-base P1-ws P1-ejb-servidor-remoto P1-ejb-cliente-remoto; do
	cd $i
	ant limpiar-todo todo
	cd -
done