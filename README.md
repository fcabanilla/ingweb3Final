# Plantilla IW3 / IUA / 2017

Creado con propósitos didácticos

Autor: Mariano García Mattío - email: [magm3333@gmail.com](mailto:magm3333@gmail.com) - Twitter: [@magm3333](https://twitter.com/magm3333)


### Instalar y Ejecutar

Para ejecutar se requiere JRE 1.8.
Para compilar y empaquetar, se requiere Maven 3.x
 

```
$ git clone https://github.com/magm3333/ing-web3-2017
$ cd ing-web3-2017
```

#### WAR (default):
```
mvn clean package [-DskipTests]
```
o bien
```
mvn clean package -Dbuild=war [-DskipTests]
```

#### JAR:
```
mvn clean package -Dbuild=jar [-DskipTests]
```
Una vez empaquetado el JAR, en la carpeta target se encontrará el archivo ti.jar, se puede ejecutar de la siguiente forma:

```
$ java -jar ti.jar
```
