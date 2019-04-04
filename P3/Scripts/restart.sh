#!/bin/bash
echo "Conectando a Maquina 1"
sshpass -p '2019sid0s' ssh si2@10.2.5.1 "/opt/glassfish4/glassfish/bin/asadmin restart-domain domain1; echo '2019sid0s' | sudo -kS service postgresql-8.4 restart"

echo "Conectando a Maquina 2"
sshpass -p '2019sid0s' ssh si2@10.2.5.2 "/opt/glassfish4/glassfish/bin/asadmin restart-domain domain1"

echo "Conectando a Maquina 3"
sshpass -p '2019sid0s' ssh si2@10.2.5.3 "/opt/glassfish4/glassfish/bin/asadmin restart-domain domain1"