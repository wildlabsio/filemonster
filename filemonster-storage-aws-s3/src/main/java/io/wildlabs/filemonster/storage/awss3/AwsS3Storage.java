package io.wildlabs.filemonster.storage.awss3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import io.wildlabs.filemonster.core.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

public class AwsS3Storage implements Storage {
    private AmazonS3 amazonS3;
    private String bucketName;

    public AwsS3Storage(AmazonS3 amazonS3, String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    @Override
    public byte[] read(String key) throws FileNotFoundException {
        try {
            if (!exists(key)) {
                throw new FileNotFoundException();
            }

            S3Object s3object = amazonS3.getObject(bucketName, key);

            return s3object.getObjectContent().readAllBytes();
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public void write(String key, byte[] content) {
        amazonS3.putObject(bucketName, key, new ByteArrayInputStream(content), null);
    }

    @Override
    public boolean exists(String key) {
        return amazonS3.doesObjectExist(bucketName, key);
    }

    @Override
    public void delete(String key) throws FileNotFoundException {
        if (!exists(key)) {
            throw new FileNotFoundException();
        }
        amazonS3.deleteObject(bucketName, key);
    }

    @Override
    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        if (!exists(sourceKey)) {
            throw new FileNotFoundException();
        }
        amazonS3.copyObject(bucketName, sourceKey, bucketName, targetKey);
    }
}
