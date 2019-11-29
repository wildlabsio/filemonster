# FileMonster
[![Build Status](https://github.com/wildlabsio/filemonster/workflows/filemonster-core/badge.svg)](https://github.com/wildlabsio/filemonster)

FileMonster provides a abstract file system. Heavily inspired by https://github.com/KnpLabs/Gaufrette
 
## Storages

The following storages are implemented right now.

* **AWS S3**

[![Build Status](https://github.com/wildlabsio/filemonster/workflows/filemonster-storage-aws-s3/badge.svg)](https://github.com/wildlabsio/filemonster) 
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-aws-s3
* **Wasabi** 

[![Build Status](https://github.com/wildlabsio/filemonster/workflows/filemonster-storage-wasabi/badge.svg)](https://github.com/wildlabsio/filemonster)
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-wasabi
* **GridFS**

[![Build Status](https://github.com/wildlabsio/filemonster/workflows/filemonster-storage-gridfs/badge.svg)](https://github.com/wildlabsio/filemonster) 
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-gridfs
* **Dropbox**
 
[![Build Status](https://github.com/wildlabsio/filemonster/workflows/filemonster-storage-dropbox/badge.svg)](https://github.com/wildlabsio/filemonster)
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-dropbox
* **Local**

[![Build Status](https://github.com/wildlabsio/filemonster/workflows/filemonster-storage-local/badge.svg)](https://github.com/wildlabsio/filemonster) 
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-local

Feel free to implement your own storage. You only have to implement the Storage interface. FileMonster also provides a
 ChainStorage to combine multiple storages.

## Installation

Provide some installation instruction.

## Usage

Provide some usage instructions.

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
