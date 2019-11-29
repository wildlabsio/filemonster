package io.wildlabs.filemonster.storage.awss3.resolver;

import com.amazonaws.services.s3.AmazonS3;
import io.wildlabs.filemonster.core.exception.ResolveException;
import io.wildlabs.filemonster.core.resolver.Resolver;

import java.net.URL;

public class AwsS3PublicUrlResolver implements Resolver {
    private final AmazonS3 amazonS3;
    private final String bucketName;

    public AwsS3PublicUrlResolver(AmazonS3 amazonS3, String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    @Override
    public String resolve(String key) throws ResolveException {
        try {
            URL url = amazonS3.getUrl(bucketName, key);
            return url.toString();
        } catch (Exception e) {
            throw new ResolveException(e);
        }
    }
}
