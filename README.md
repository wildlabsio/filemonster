# FileMonster
[![Build Status](https://travis-ci.org/wildlabsio/filemonster.svg?branch=master)](https://travis-ci.org/wildlabsio/filemonster)

This library provides an abstract file system. Inspired by https://github.com/KnpLabs/Gaufrette
 
## Adapter

All adapters are seperated projects to reduce dependencies and complexity.

* **GridFS** https://github.com/MallorcaSoftware/FileStorageGridFS
* **Dropbox** https://github.com/MallorcaSoftware/FileStorageDropbox
* **Local** https://github.com/MallorcaSoftware/FileStorageLocal
* **Chain** https://github.com/MallorcaSoftware/FileStorageChain

Feel free to implement own adapter. You only have to implement the Adapter interface.

## Installation

This library isn´t available in any repositories yet. You have to install it to your Maven repository. You have to clone the project and simply execute:

```shell
mvn clean install
```

Afterwars you can include ImageManipulation as a dependency in your maven project

```shell
<dependency>
    <groupId>io.wildlabs</groupId>
    <artifactId>filemonster</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Todos
This library is still in development. Don´t use it in production mode without testing everything by yourself.

.
