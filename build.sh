#!/usr/bin/env bash

mvn -B package --file filemonster-core/pom.xml
mvn -B package --file filemonster-storage-aws-s3/pom.xml
mvn -B package --file filemonster-storage-dropbox/pom.xml
mvn -B package --file filemonster-storage-gridfs/pom.xml
mvn -B package --file filemonster-storage-local/pom.xml
mvn -B package --file filemonster-storage-wasabi/pom.xml
