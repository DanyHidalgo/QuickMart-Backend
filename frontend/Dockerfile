# Usar una imagen base de Node.js
FROM node:14

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar los archivos de la aplicación al contenedor
COPY package*.json ./
COPY ./ ./

# Instalar dependencias
RUN npm install

# Construir la aplicación
RUN npm run build

# Exponer el puerto que la aplicación usará
EXPOSE 3000

# Comando para iniciar la aplicación
CMD ["npm", "start"]
