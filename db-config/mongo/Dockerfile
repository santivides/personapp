# Dockerfile para MongoDB

# Usa la imagen oficial de MongoDB
FROM mongo:latest

# Establece el directorio de trabajo
WORKDIR /docker-entrypoint-initdb.d

# Copia el archivo de inicialización JS
COPY ../../scripts/persona_ddl_mongo.js /docker-entrypoint-initdb.d/

# Expone el puerto 27017
EXPOSE 27017

# Define el comando para iniciar MongoDB (usará el entrypoint predeterminado)
CMD ["mongod"]
