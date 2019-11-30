package io.wildlabs.filemonster.core;

import java.io.FileNotFoundException;

public interface Filesystem {
    boolean has(String key);

    void rename(String sourceKey, String targetKey) throws FileNotFoundException;

    byte[] read(String key) throws FileNotFoundException;

    void write(String key, byte[] content);

    void delete(String key) throws FileNotFoundException;
}
