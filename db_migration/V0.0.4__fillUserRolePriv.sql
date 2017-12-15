INSERT INTO `privileges` (`description`, `name`) VALUES ('ESCRIBIR', 'WRITE');
INSERT INTO `privileges` (`description`, `name`) VALUES ('MODIFICAR', 'UPDATE');
INSERT INTO `privileges` (`description`, `name`) VALUES ('LEER', 'READ');
INSERT INTO `privileges` (`description`, `name`) VALUES ('BORRAR', 'DELETE');
INSERT INTO `privileges` (`description`, `name`) VALUES ('AGREGAR', 'APPEND');
INSERT INTO `privileges` (`description`, `name`) VALUES ('EJECUTAR', 'EXECUTE');


INSERT INTO `roles` (`description`,`name`) VALUES ('Administrador','ROLE_ADMIN');
INSERT INTO `roles` (`description`,`name`) VALUES ('Usuario','ROLE_USER');


INSERT INTO `users` (`accountEnabled`,`accountExpired`,`accountLocked`,`credentialsExpired`,`email`,`firstName`,`lastName`,`password`,`username`) VALUES (1,0,0,0,'user@mail.com','User','One','123','user');
INSERT INTO `users` (`accountEnabled`,`accountExpired`,`accountLocked`,`credentialsExpired`,`email`,`firstName`,`lastName`,`password`,`username`) VALUES (1,0,0,0,'admin@mail.com','Admin','Two','123','admin');

INSERT INTO `userroles` (`idUser`, `idRole`) VALUES ('1', '2');
INSERT INTO `userroles` (`idUser`, `idRole`) VALUES ('2', '1');
INSERT INTO `userroles` (`idUser`, `idRole`) VALUES ('2', '2');



