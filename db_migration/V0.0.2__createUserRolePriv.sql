DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `accountEnabled` tinyint(4) NOT NULL,
  `accountExpired` tinyint(4) NOT NULL,
  `accountLocked` tinyint(4) NOT NULL,
  `credentialsExpired` tinyint(4) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `privileges`;

CREATE TABLE `privileges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m2tnonbcaquofx1ccy060ejyc` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `userroles`;

CREATE TABLE `userroles` (
  `idUser` int(11) NOT NULL,
  `idRole` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idRole`),
  KEY `FK_kii45804t1tnfyexjurgk9u4` (`idRole`),
  CONSTRAINT `FK_kii45804t1tnfyexjurgk9u4` FOREIGN KEY (`idRole`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_ln5gp4pr3325gnwrlpahmtp7y` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `userprivileges`;

CREATE TABLE `userprivileges` (
  `idUser` int(11) NOT NULL,
  `idPrivilege` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idPrivilege`),
  KEY `FK_pkkmr4ud85uuh1u3goaya3oof` (`idPrivilege`),
  CONSTRAINT `FK_ivqpk9ebss5u47x1wfl9lq13j` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`),
  CONSTRAINT `FK_pkkmr4ud85uuh1u3goaya3oof` FOREIGN KEY (`idPrivilege`) REFERENCES `privileges` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `roleprivileges`;

CREATE TABLE `roleprivileges` (
  `idRole` int(11) NOT NULL,
  `idPrivilege` int(11) NOT NULL,
  PRIMARY KEY (`idRole`,`idPrivilege`),
  KEY `FK_3j7dy62pf0gdsjvsdcm95943e` (`idPrivilege`),
  CONSTRAINT `FK_3j7dy62pf0gdsjvsdcm95943e` FOREIGN KEY (`idPrivilege`) REFERENCES `privileges` (`id`),
  CONSTRAINT `FK_6raf87bs33bcb2ub3gtbaucfy` FOREIGN KEY (`idRole`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




