-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-08-2020 a las 11:50:45
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `tiendadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE IF NOT EXISTS `departamento` (
  `id_departamento` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id_departamento`, `nombre`) VALUES
(1, 'Aseo'),
(2, 'Peleteria'),
(3, 'Jugueteria'),
(4, 'Ferreteria'),
(5, 'Electronica'),
(6, 'Utiles para el hogar'),
(7, 'Textileria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `depart_product`
--

CREATE TABLE IF NOT EXISTS `depart_product` (
  `id_departamento` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  PRIMARY KEY (`id_departamento`,`id_producto`),
  KEY `FK7qxaikegc1x4mekd4xnongp4l` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `depart_product`
--

INSERT INTO `depart_product` (`id_departamento`, `id_producto`) VALUES
(4, 38),
(5, 38),
(6, 38),
(7, 39),
(4, 40),
(5, 40),
(6, 40),
(1, 41),
(6, 41),
(2, 42),
(2, 43),
(1, 45),
(2, 45),
(5, 45);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `id_producto` bigint(20) NOT NULL AUTO_INCREMENT,
  `cant_disp` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `cant_disp`, `descripcion`, `foto`, `nombre`, `precio`) VALUES
(38, 50, 'TV de 32'''' 4K con caja decodificadora', '', 'TV', 500),
(39, 800, 'asdsadsadsakjdn', '', 'Pantalones', 34),
(40, 455, 'dsfllkfdfklfkl', '', 'Lavadora', 445),
(41, 5999, 'Pasta de Dientes Colgate', '', 'Pasta', 1),
(42, 4556, 'skldnaslkdaslkdn', '', 'Tenis', 34),
(43, 778, 'dlkfksdlfmekldklfmsdf', '', 'Chancletas', 34),
(45, 556, 'fsfsdfdggsgfdg\n', '', 'L;MVM;LV', 34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tienda`
--

CREATE TABLE IF NOT EXISTS `tienda` (
  `id_tienda` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tienda`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Volcado de datos para la tabla `tienda`
--

INSERT INTO `tienda` (`id_tienda`, `nombre`) VALUES
(2, 'Carlos III'),
(3, 'La Puntilla'),
(6, 'Mercado 3 y 70'),
(8, 'otra mas'),
(13, 'otras mas'),
(14, 'otra tienda'),
(15, 'una mas'),
(16, 'muchas mas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiend_depart`
--

CREATE TABLE IF NOT EXISTS `tiend_depart` (
  `id_tienda` bigint(20) NOT NULL,
  `id_departamento` bigint(20) NOT NULL,
  PRIMARY KEY (`id_tienda`,`id_departamento`),
  KEY `FKk32b1qa1p35rwbibjuyct2ojx` (`id_departamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tiend_depart`
--

INSERT INTO `tiend_depart` (`id_tienda`, `id_departamento`) VALUES
(14, 1),
(16, 1),
(8, 2),
(13, 2),
(6, 3),
(8, 3),
(15, 3),
(16, 3),
(6, 6);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `depart_product`
--
ALTER TABLE `depart_product`
  ADD CONSTRAINT `FK7qxaikegc1x4mekd4xnongp4l` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `FK97kw6bwteivxhir2ne6q5ig0g` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`);

--
-- Filtros para la tabla `tiend_depart`
--
ALTER TABLE `tiend_depart`
  ADD CONSTRAINT `FKk32b1qa1p35rwbibjuyct2ojx` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`),
  ADD CONSTRAINT `FKry4mfwhxoaajfy5a38khasi01` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id_tienda`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
