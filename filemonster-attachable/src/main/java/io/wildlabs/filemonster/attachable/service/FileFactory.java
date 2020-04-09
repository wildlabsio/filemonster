package io.wildlabs.filemonster.attachable.service;

import io.wildlabs.filemonster.attachable.model.File;

public interface FileFactory<T extends File> {
    T createFile(String classIdentifier, Long referenceId, String fileKey, String type);
}
