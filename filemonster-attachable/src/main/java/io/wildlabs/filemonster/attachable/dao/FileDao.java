package io.wildlabs.filemonster.attachable.dao;

import io.wildlabs.filemonster.attachable.model.File;

import java.util.List;
import java.util.Optional;

public interface FileDao<T extends File> {
    List<T> findByClassIdentifierAndReferenceIdAndType(String classIdentifier, Long referenceId, String type);

    Optional<T> findTopByClassIdentifierAndReferenceIdAndType(String classIdentifier, Long referenceId, String type);

    void delete(T file);

    T save(T file);
}
