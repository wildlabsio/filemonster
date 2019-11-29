package io.wildlabs.filemonster.storage.awss3.resolver;

import com.amazonaws.services.s3.AmazonS3;
import io.wildlabs.filemonster.core.exception.ResolveException;
import io.wildlabs.filemonster.core.resolver.Resolver;

import java.net.URL;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class AwsS3PresignedUrlResolver implements Resolver {
    private final AmazonS3 amazonS3;
    private final String bucketName;
    private final TemporalAmount expiration;

    public AwsS3PresignedUrlResolver(AmazonS3 amazonS3, String bucketName, TemporalAmount expiration) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
        this.expiration = expiration;
    }

    @Override
    public String resolve(String key) throws ResolveException {
        try {
            URL url = amazonS3.generatePresignedUrl(
                    bucketName,
                    key,
                    Date.from(Instant.from(ZonedDateTime.now().plus(expiration)))
            );

            return url.toString();
        } catch (Exception e) {
            throw new ResolveException(e);
        }
    }
}
