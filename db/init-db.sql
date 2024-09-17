-- Elimina la base de datos si existe
DROP DATABASE IF EXISTS quickmart;

-- Crea la base de datos
CREATE DATABASE IF NOT EXISTS quickmart;

-- Usa la base de datos
USE quickmart;

-- Crea la tabla usuario (con correo electrónico y contraseña cifrada)
CREATE TABLE IF NOT EXISTS usuario (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       nombre VARCHAR(50),
                                       apellido VARCHAR(50),
                                       nombre_usuario VARCHAR(50) UNIQUE,
                                       correo VARCHAR(100) UNIQUE, -- Correo electrónico único
                                       contrasena VARCHAR(255) -- Contraseña cifrada (bcrypt)
);

-- Crea la tabla torneo
CREATE TABLE IF NOT EXISTS torneo (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nombre VARCHAR(100)
);

-- Crea la tabla equipo (ahora con torneo_id)
CREATE TABLE IF NOT EXISTS equipo (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nombre VARCHAR(50),
                                      torneo_id BIGINT, -- Relación con torneo
                                      FOREIGN KEY (torneo_id) REFERENCES torneo(id) ON DELETE CASCADE
);

-- Crea la tabla grupo
CREATE TABLE IF NOT EXISTS grupo (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     nombre VARCHAR(50),
                                     torneo_id BIGINT,
                                     FOREIGN KEY (torneo_id) REFERENCES torneo(id) ON DELETE CASCADE
);

-- Crea la tabla de unión usuario_grupo
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
                                       equipo_local_id BIGINT,
                                       equipo_visitante_id BIGINT,
                                       goles_local INT NULL,
                                       goles_visitante INT NULL,
                                       FOREIGN KEY (equipo_local_id) REFERENCES equipo(id) ON DELETE CASCADE,
                                       FOREIGN KEY (equipo_visitante_id) REFERENCES equipo(id) ON DELETE CASCADE
);

-- Crea la tabla prediccion (con goles esperados y puntos ganados)
CREATE TABLE IF NOT EXISTS prediccion (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          usuario_id BIGINT,
                                          partido_id BIGINT,
                                          grupo_id BIGINT, -- Relación con el grupo
                                          goles_local_esperado INT NULL, -- Goles esperados para el equipo local
                                          goles_visitante_esperado INT NULL, -- Goles esperados para el equipo visitante
                                          puntos_ganados INT DEFAULT 0, -- Puntos ganados en la predicción
                                          FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
                                          FOREIGN KEY (partido_id) REFERENCES partido(id) ON DELETE CASCADE,
                                          FOREIGN KEY (grupo_id) REFERENCES grupo(id) ON DELETE CASCADE
);

-- Inserta datos en las tablas
-- Torneos
INSERT INTO torneo (nombre) VALUES ('Torneo 1'), ('Torneo 2');

-- Equipos (con torneo_id asociado)
INSERT INTO equipo (nombre, torneo_id) VALUES
                                           ('Equipo A', 1),
                                           ('Equipo B', 1),
                                           ('Equipo C', 1),
                                           ('Equipo D', 1),
                                           ('Equipo E', 2),
                                           ('Equipo F', 2),
                                           ('Equipo G', 2),
                                           ('Equipo H', 2);

-- Grupos
INSERT INTO grupo (nombre, torneo_id) VALUES
                                          ('Grupo 1', 1),
                                          ('Grupo 2', 1),
                                          ('Grupo 3', 2),
                                          ('Grupo 4', 2);

-- Usuarios
INSERT INTO usuario (nombre, apellido, nombre_usuario, correo, contrasena) VALUES
                                                                               ('Juan', 'Pérez', 'juanperez', 'juan@example.com', '$2a$10$N0aHsS3KfZZ.V9YrxJz0pui6Vz8jfg4FoXZZzwrPf/zk13RzIWbKC'),
                                                                               ('Ana', 'Gómez', 'anagomez', 'ana@example.com', '$2a$10$N0aHsS3KfZZ.V9YrxJz0pui6Vz8jfg4FoXZZzwrPf/zk13RzIWbKC'),
                                                                               ('Luis', 'Martínez', 'luismartinez', 'luis@example.com', '$2a$10$N0aHsS3KfZZ.V9YrxJz0pui6Vz8jfg4FoXZZzwrPf/zk13RzIWbKC'),
                                                                               ('Marta', 'Rodríguez', 'martarodriguez', 'marta@example.com', '$2a$10$N0aHsS3KfZZ.V9YrxJz0pui6Vz8jfg4FoXZZzwrPf/zk13RzIWbKC');

-- Asocia usuarios con grupos
INSERT INTO usuario_grupo (usuario_id, grupo_id, puntaje) VALUES
                                                              (1, 1, 0), (2, 1, 0), (3, 1, 0), (4, 1, 0),
                                                              (1, 2, 0), (2, 2, 0), (3, 2, 0), (4, 2, 0),
                                                              (1, 3, 0), (2, 3, 0), (3, 3, 0), (4, 3, 0),
                                                              (1, 4, 0), (2, 4, 0), (3, 4, 0), (4, 4, 0);

-- Inserta partidos para el Torneo 1
INSERT INTO partido (ronda, fecha_inicio, equipo_local_id, equipo_visitante_id, goles_local, goles_visitante) VALUES
                                                                                                                  ('Jornada 1', '2024-09-01', 1, 2, 1, 0),
                                                                                                                  ('Jornada 1', '2024-09-01', 3, 4, 2, 1),
                                                                                                                  ('Jornada 2', '2024-09-15', 1, 3, NULL, NULL),
                                                                                                                  ('Jornada 2', '2024-09-15', 2, 4, NULL, NULL),
                                                                                                                  ('Jornada 3', '2024-09-22', 1, 4, NULL, NULL),
                                                                                                                  ('Jornada 3', '2024-09-22', 2, 3, NULL, NULL);

-- Inserta predicciones para el Torneo 1
INSERT INTO prediccion (usuario_id, partido_id, grupo_id, goles_local_esperado, goles_visitante_esperado) VALUES
                                                                                                              (1, 1, 1, 1, 0),
                                                                                                              (2, 1, 1, 2, 1),
                                                                                                              (3, 1, 1, 1, 1),
                                                                                                              (4, 1, 1, 0, 1),
                                                                                                              (1, 2, 1, 2, 1),
                                                                                                              (2, 2, 1, 1, 0),
                                                                                                              (3, 2, 1, 3, 1),
                                                                                                              (4, 2, 1, 1, 2),
                                                                                                              (2, 3, 1, 1, 2),
                                                                                                              (3, 3, 1, 0, 1),
                                                                                                              (4, 3, 1, 2, 1),
                                                                                                              (2, 4, 1, 0, 2),
                                                                                                              (3, 4, 1, 1, 1),
                                                                                                              (4, 4, 1, 3, 0);

-- Inserta partidos para el Torneo 2
INSERT INTO partido (ronda, fecha_inicio, equipo_local_id, equipo_visitante_id, goles_local, goles_visitante) VALUES
                                                                                                                  ('Jornada 1', '2024-10-01', 5, 6, 1, 1),
                                                                                                                  ('Jornada 1', '2024-10-01', 7, 8, 2, 2),
                                                                                                                  ('Jornada 2', '2024-10-15', 5, 7, NULL, NULL),
                                                                                                                  ('Jornada 2', '2024-10-15', 6, 8, NULL, NULL),
                                                                                                                  ('Jornada 3', '2024-10-22', 5, 8, NULL, NULL),
                                                                                                                  ('Jornada 3', '2024-10-22', 6, 7, NULL, NULL);

-- Inserta predicciones para el Torneo 2
INSERT INTO prediccion (usuario_id, partido_id, grupo_id, goles_local_esperado, goles_visitante_esperado) VALUES
                                                                                                              (1, 7, 3, 2, 1),
                                                                                                              (2, 7, 3, 1, 2),
                                                                                                              (3, 7, 3, 0, 0),
                                                                                                              (4, 7, 3, 3, 2),
                                                                                                              (1, 8, 3, 1, 2),
                                                                                                              (2, 8, 3, 2, 1),
                                                                                                              (3, 8, 3, 2, 2),
                                                                                                              (4, 8, 3, 1, 1),
                                                                                                              (1, 9, 3, 2, 0),
                                                                                                              (2, 9, 3, 1, 1),
                                                                                                              (3, 9, 3, 0, 2),
                                                                                                              (4, 9, 3, 2, 1),
                                                                                                              (1, 10, 3, 1, 2),
                                                                                                              (2, 10, 3, 2, 1),
                                                                                                              (3, 10, 3, 1, 1);
