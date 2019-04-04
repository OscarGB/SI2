#!/bin/bash
echo "Conectando a Maquina 1"
sshpass -p '2019sid0s' ssh si2@10.2.5.1 "export J2EE_HOME=/opt/glassfish-4.1.2/glassfish; /opt/glassfish4/glassfish/bin/asadmin start-domain domain1"

echo "Conectando a Maquina 2"
sshpass -p '2019sid0s' ssh si2@10.2.5.2 "export J2EE_HOME=/opt/glassfish-4.1.2/glassfish; /opt/glassfish4/glassfish/bin/asadmin start-domain domain1; echo '2019sid0s' | sudo -kS service postgresql-8.4 stop"

echo "Conectando a Maquina 3"
sshpass -p '2019sid0s' ssh si2@10.2.5.3 "export J2EE_HOME=/opt/glassfish-4.1.2/glassfish; /opt/glassfish4/glassfish/bin/asadmin start-domain domain1; echo '2019sid0s' | sudo -kS service postgresql-8.4 stop"

