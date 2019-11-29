package io.wildlabs.filemonster.core;

import io.wildlabs.filemonster.core.storage.Storage;

import java.io.FileNotFoundException;

public class FilesystemImpl implements Filesystem {
    private final Storage storage;

    public FilesystemImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean has(String key) {
        return storage.exists(key);
    }

    @Override
    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        if (!has(sourceKey)) {
            throw new FileNotFoundException();
        }

        storage.rename(sourceKey, targetKey);
    }

    @Override
    public byte[] read(String key) throws FileNotFoundException {
        return storage.read(key);
    }

    @Override
    public void write(String key, byte[] content) {
        storage.write(key, content);
    }

    @Override
    public void delete(String key) throws FileNotFoundException {
        if (!has(key)) {
            throw new FileNotFoundException();
        }

        storage.delete(key);
    }
}
