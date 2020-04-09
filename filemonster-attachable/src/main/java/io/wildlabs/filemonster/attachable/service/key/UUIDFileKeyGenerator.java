package io.wildlabs.filemonster.attachable.service.key;

import io.wildlabs.filemonster.attachable.model.FileAttachable;

import java.util.UUID;

public class UUIDFileKeyGenerator implements FileKeyGenerator {
    @Override
    public String generateFileKey(FileAttachable fileAttachable, String type) {
        return UUID.randomUUID().toString();
    }
}
