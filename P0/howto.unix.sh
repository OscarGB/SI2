# man: Ayuda de un mandato
man ps # ayuda de ps
# ps: lista procesos
ps -aefl # procesos en ejecución
# grep: busca cadenas o expresiones regulares
ps -aefl | grep apache # muestra procesos de apache
# cat: muestra archivo
cat estilo.css
# more y less: paginan pantalla
cat estilo.css | more
# awk: procesa línea
cat estilo.css | awk '{for (i=1;i<=NF;i++) print $i;}' # muestra todas las palabras en estilo.css
# sort: ordena
cat estilo.css | awk '{for (i=1;i<=NF;i++) print $i;}' | grep ^.*: | sort -u # muestra atributos usados en .css
cat estilo.css | awk '{for (i=1;i<=NF;i++) print $i;}' | grep ^.*: | sort | uniq -c # muestra y cuenta atributos usados en .css
# sed: cambia cadena
cat estilo.css | sed 's/logo.png/logo.jpg/g'
# tr: cambia o elimina caracteres
cat estilo.css | tr -d '\n' # comprime a una línea
# ls: lista archivos
ls -al $HOME # lista archivos del directorio home, con sus permisos
# find: busca archivos
find . -mtime 1 # busca archivos cambiados en el último día en directorio actual y subdirectorios
# du: espacio en disco usado
du . | sort -n # muestra espacio usado por directorios, ordenados de menor a mayor
# head y tail: principio o final de archivo
du . | sort -n | tail -50 # últimas 50 líneas
# df: muestra espacio libre de filesystems
df -k
# mount: muestra filesystems montados, o los monta
mount
# chmod: cambia permisos
chmod -R a+rwX $HOME/public_html # da permisos a todos de lectura/escritura y de ejecución, si ya tenía permiso de ejecución
# mkdir: crea directorio
mkdir $HOME/public_html
# sudo: ejecuta un mandato con privilegios de root, el superusuario
# halt y reboot: para o reinicia
sudo halt # para apagar
# cp, mv : copian o renombran archivos o directorios
cp -pr old_dir new_dir # copia directorio old_dir y todo su contenido a new_dir
# ssh: abre sesión interactiva en sistema remoto
ssh si2@10.10.1.1 # abre sesión en servidor con IP 10.10.1.1 para el usuario si2
# scp: como cp, pero para copias entre dos servidores
scp mi_fichero si2@10.10.1.1:~/ # copia mi_fichero al servidor 10.10.1.1 en el directorio $HOME del usuario si2

