# FileStorage
[![Build Status](https://travis-ci.org/MallorcaSoftware/FileStorage.svg?branch=develop)](https://travis-ci.org/MallorcaSoftware/FileStorage)

This library provides an abstract file system. Inspired by https://github.com/KnpLabs/Gaufrette

## Changelog
All changes to this project will be documented in this file.

### [1.2.0] - [not released]

### [1.1.0] - 19.09.2019
* Added getKeys to Filesystem
* Added tests for Filesystem
* Added documentation for Filesystem

### [1.0.0] - 29.01.2018
* Initial project
 
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
    <groupId>com.mallorcasoftware</groupId>
    <artifactId>filestorage</artifactId>
    <version>1.2-SNAPSHOT</version>
</dependency>
```

## Todos
This library is still in development. Don´t use it in production mode without testing everything by yourself.
