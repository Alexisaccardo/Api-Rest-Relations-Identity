-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 19-10-2023 a las 20:52:49
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `identities`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `identity`
--

DROP TABLE IF EXISTS `identity`;
CREATE TABLE IF NOT EXISTS `identity` (
  `document` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `cellphone` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`document`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `identity`
--

INSERT INTO `identity` (`document`, `name`, `cellphone`, `address`) VALUES
('209888', 'MeLi', '3225678786', 'Medellin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relations`
--

DROP TABLE IF EXISTS `relations`;
CREATE TABLE IF NOT EXISTS `relations` (
  `id` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `ally` varchar(30) NOT NULL,
  `document_identity` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `relations`
--

INSERT INTO `relations` (`id`, `name`, `ally`, `document_identity`) VALUES
('', 'Alexis', 'MeLi', '209888'),
('eb47a3', 'Johan Alexis', 'MeLi', '209888');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
