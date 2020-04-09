# FileMonster
[![Build Status](https://github.com/wildlabsio/filemonster/workflows/filemonster/badge.svg)](https://github.com/wildlabsio/filemonster)

FileMonster provides an abstract filesystem layer.
 
## Storages

The following storage's are implemented right now.

### **Amazon S3**
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-aws-s3
### **Wasabi** 
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-aws-s3
### **GridFS**
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-gridfs
### **Dropbox**
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-dropbox
### **Local**
https://github.com/wildlabsio/filemonster/tree/master/filemonster-storage-local

Feel free to implement your own storage. You only have to implement the Storage interface. FileMonster also provides a
 ChainStorage to combine multiple storage's.

## Installation

The FileMonster artifacts are stored on github. Look into https://github.com/wildlabsio/filemonster/packages to get a
 list of current artifacts.

## Usage

To start you have to create a Storage of your choice.

```java
// build your amazon s3 client based on your properties
AmazonS3 amazonS3 = AmazonS3ClientBuilder.defaultClient();

// create an AwsS3Storage with your generated AmazonS3 client
Storage storage = new AwsS3Storage(amazonS3, "your-bucket-name");

// create a Filesystem with your configured Storage
Filesystem filesystem = new FilesystemImpl(storage);

// start to use your configured Filesystem
filesystem.read("some-key");
```

## Attachable

FileMonster provides a modules to attach File to a FileAttachable. 

### Maven dependency
```java
<dependency>
    <groupId>io.wildlabs</groupId>
    <artifactId>filemonster-attachable</artifactId>
    <version>${filemonster-attachable.version}</version>
</dependency>
```

### Entity
Create an Entity which implements File interface. This entity will represent the attached file in your database.
#### FileFactory
Implement a FileFactory which will create an instance of your File.
#### Dao
Implement a FileDao which implements FileDao interface.

### Service
Use FileService to handle File attachment.

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
