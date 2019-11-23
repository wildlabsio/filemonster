package io.wildlabs.filemonster.core;

import io.wildlabs.filemonster.core.storage.Storage;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Filesystem {
    private Storage storage;

    public Filesystem(Storage storage) {
        this.storage = storage;
    }

    public boolean has(String key) {
        return storage.exists(key);
    }

    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        if (!has(sourceKey)) {
            throw new FileNotFoundException();
        }

        storage.rename(sourceKey, targetKey);
    }

    public byte[] read(String key) throws FileNotFoundException {
        return storage.read(key);
    }

    public void write(String key, byte[] content) {
        storage.write(key, content);
    }

    public void delete(String key) throws FileNotFoundException {
        if (!has(key)) {
            throw new FileNotFoundException();
        }

        storage.delete(key);
    }
}
