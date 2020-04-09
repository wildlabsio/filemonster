package io.wildlabs.filemonster.attachable.service;

import io.wildlabs.filemonster.attachable.dao.FileDao;
import io.wildlabs.filemonster.attachable.model.File;
import io.wildlabs.filemonster.attachable.model.FileAttachable;
import io.wildlabs.filemonster.attachable.service.key.FileKeyGenerator;
import io.wildlabs.filemonster.core.Filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class FileService<T extends File> {
    private final FileDao<T> fileDao;
    private final FileFactory<T> fileFactory;
    private final FileKeyGenerator fileKeyGenerator;
    private final Filesystem filesystem;

    public FileService(
            FileDao<T> fileDao,
            FileFactory<T> fileFactory,
            FileKeyGenerator fileKeyGenerator,
            Filesystem filesystem
    ) {
        this.fileDao = fileDao;
        this.fileFactory = fileFactory;
        this.fileKeyGenerator = fileKeyGenerator;
        this.filesystem = filesystem;
    }

    public Optional<T> getFile(FileAttachable fileAttachable, String type) {
        return fileDao.findTopByClassIdentifierAndReferenceIdAndType(
                fileAttachable.getClassIdentifier(),
                fileAttachable.getReferenceId(),
                type
        );
    }

    public List<T> getFiles(FileAttachable fileAttachable, String type) {
        return fileDao.findByClassIdentifierAndReferenceIdAndType(
                fileAttachable.getClassIdentifier(),
                fileAttachable.getReferenceId(),
                type
        );
    }

    public void deleteFile(T file) {
        try {
            filesystem.delete(file.getFileKey());
        } catch (FileNotFoundException ignored) {
        } finally {
            fileDao.delete(file);
        }
    }

    public T setFile(FileAttachable fileAttachable, String type, InputStream inputStream) throws IOException {
        Optional<T> existingFile = getFile(fileAttachable, type);
        existingFile.ifPresent(this::deleteFile);

        return storeFile(fileAttachable, type, inputStream);
    }

    private T storeFile(FileAttachable fileAttachable, String type, InputStream inputStream) throws IOException {
        String key = fileKeyGenerator.generateFileKey(fileAttachable, type);

        filesystem.write(key, inputStream.readAllBytes());

        T file = fileFactory.createFile(
                fileAttachable.getClassIdentifier(),
                fileAttachable.getReferenceId(),
                key,
                type
        );

        return fileDao.save(file);
    }
}
