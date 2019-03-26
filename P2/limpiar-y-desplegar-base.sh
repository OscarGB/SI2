#!/bin/bash
cd $(dirname $0)

for i in P1-base; do
	cd $i
	ant replegar; ant delete-pool-local
	cd -
done

for i in P1-base; do
	cd $i
	ant limpiar-todo todo
	cd -
done