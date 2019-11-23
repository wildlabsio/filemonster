package io.wildlabs.filemonster;

import io.wildlabs.filemonster.adapter.Adapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class Filesystem {
    private Adapter adapter;

    public Filesystem(Adapter adapter) {
        this.adapter = adapter;
    }

    public boolean has(String key) {
        return adapter.exists(key);
    }

    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        if (!has(sourceKey)) {
            throw new FileNotFoundException();
        }

        adapter.rename(sourceKey, targetKey);
    }

    public InputStream read(String key) throws FileNotFoundException {
        return adapter.read(key);
    }

    public void write(String key, InputStream content) {
        adapter.write(key, content);
    }

    public void delete(String key) throws FileNotFoundException {
        if (!has(key)) {
            throw new FileNotFoundException();
        }

        adapter.delete(key);
    }

    public List<String> getKeys() {
        return adapter.getKeys();
    }
}
