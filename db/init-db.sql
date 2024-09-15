-- Crea la base de datos
CREATE DATABASE IF NOT EXISTS quickmart;

-- Usa la base de datos
USE quickmart;

-- Crea la tabla usuario
CREATE TABLE IF NOT EXISTS usuario (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       nombre VARCHAR(50),
    apellido VARCHAR(50)
    );

-- Crea la tabla equipo
CREATE TABLE IF NOT EXISTS equipo (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nombre VARCHAR(50)
    );

-- Crea la tabla torneo (ahora con campo nombre)
CREATE TABLE IF NOT EXISTS torneo (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nombre VARCHAR(100),  -- Nuevo campo para nombre del torneo
    fecha_inicio DATE,
    fecha_final DATE
    );

-- Crea la tabla grupo
CREATE TABLE IF NOT EXISTS grupo (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     nombre VARCHAR(50),
    torneo_id BIGINT,
    FOREIGN KEY (torneo_id) REFERENCES torneo(id) ON DELETE CASCADE
    );

-- Crea la tabla usuario_grupo (tabla intermedia para puntaje)
CREATE TABLE IF NOT EXISTS usuario_grupo (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             usuario_id BIGINT,
                                             grupo_id BIGINT,
                                             puntaje INT DEFAULT 0,
                                             FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (grupo_id) REFERENCES grupo(id) ON DELETE CASCADE
    );

-- Crea la tabla partido
CREATE TABLE IF NOT EXISTS partido (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       ronda VARCHAR(50),
    resultado VARCHAR(50),
    fecha_inicio DATE,
    equipo_id BIGINT,
    FOREIGN KEY (equipo_id) REFERENCES equipo(id) ON DELETE CASCADE
    );

-- Crea la tabla partido_equipo
CREATE TABLE IF NOT EXISTS partido_equipo (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              equipo BIGINT,
                                              local BOOLEAN,
                                              visitante BOOLEAN,
                                              resultado VARCHAR(50),
    ronda VARCHAR(50),
    fecha_inicio DATE,
    partido_id BIGINT,
    FOREIGN KEY (partido_id) REFERENCES partido(id) ON DELETE CASCADE
    );
