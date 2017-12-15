DROP TABLE IF EXISTS `entidades`;

CREATE TABLE `entidades` (
  `idEntidad` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEntidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

