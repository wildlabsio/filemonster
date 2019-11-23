package io.wildlabs.filemonster.adapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Represents the filesystem implementation
 */
public interface Adapter {
    /**
     * Reads the content from the file.
     *
     * @param key key of the file
     * @return InputStream of the given file
     * @throws FileNotFoundException if file does not exist
     */
    InputStream read(String key) throws FileNotFoundException;

    /**
     * Writes the given content into the file.
     *
     * @param key     key of the file
     * @param content content of the file
     */
    void write(String key, InputStream content);

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

    /**
     * Returns all keys
     *
     * @return list of keys
     */
    List<String> getKeys();
}

