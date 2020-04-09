package io.wildlabs.filemonster.attachable.service.key;

import io.wildlabs.filemonster.attachable.model.FileAttachable;

public interface FileKeyGenerator {
    String generateFileKey(FileAttachable fileAttachable, String type);
}
