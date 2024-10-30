# Dockerfile para MariaDB

# Usa la imagen oficial de MariaDB
FROM mariadb:latest

# Establece el directorio de trabajo
WORKDIR /docker-entrypoint-initdb.d

# Copia el archivo de inicialización SQL
COPY ../../scripts/persona_ddl_maria.sql /docker-entrypoint-initdb.d/

# Expone el puerto 3307
EXPOSE 3307

# Configura las variables de entorno
ENV MYSQL_ROOT_PASSWORD=persona_db
ENV MYSQL_DATABASE=persona_db
ENV MYSQL_USER=persona_db
ENV MYSQL_PASSWORD=persona_db

# Ejecuta el entrypoint de MariaDB
CMD ["mysqld"]
