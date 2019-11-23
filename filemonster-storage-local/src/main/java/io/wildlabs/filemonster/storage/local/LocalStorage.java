package io.wildlabs.filemonster.storage.local;

import io.wildlabs.filemonster.core.storage.Storage;

import java.io.*;

class LocalStorage implements Storage {
    @Override
    public byte[] read(String key) throws FileNotFoundException {
        return null;
    }

    @Override
    public void write(String key, byte[] content) {

    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public void delete(String key) throws FileNotFoundException {

    }

    @Override
    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {

    }
}
