package io.wildlabs.filemonster.core.storage;

import java.io.FileNotFoundException;

/**
 * Represents the filesystem implementation
 */
public interface Storage {
    /**
     * Reads the content from the file.
     *
     * @param key key of the file
     * @return byte[] of the given file
     * @throws FileNotFoundException if file does not exist
     */
    byte[] read(String key) throws FileNotFoundException;

    /**
     * Writes the given content into the file.
     *
     * @param key     key of the file
     * @param content content of the file
     */
    void write(String key, byte[] content);

    /**
     * Indicates whether the file matching the specified key exists.
     *
     * @param key of the file
     * @return true if the file exists
     */
    boolean exists(String key);

    /**
     * Deletes the file matching the specified key.
     *
     * @param key of the file
     * @throws FileNotFoundException if file does not exist
     */
    void delete(String key) throws FileNotFoundException;

    /**
     * Renames the file with the given sourceKey to the targetKey
     *
     * @param sourceKey sourceKey of the file
     * @param targetKey targetKey of the file
     * @throws FileNotFoundException if sourceKey does not exist
     */
    void rename(String sourceKey, String targetKey) throws FileNotFoundException;
}

