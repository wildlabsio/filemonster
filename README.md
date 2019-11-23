# FileMonster
[![Build Status](https://travis-ci.org/wildlabsio/filemonster.svg?branch=master)](https://travis-ci.org/wildlabsio/filemonster)

FileMonster provides a abstract file system. Heavily inspired by https://github.com/KnpLabs/Gaufrette
 
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

## License

MIT License

Copyright (c) 2019 WildLabs.io

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
