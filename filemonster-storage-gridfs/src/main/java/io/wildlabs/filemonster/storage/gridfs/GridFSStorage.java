package io.wildlabs.filemonster.storage.gridfs;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import io.wildlabs.filemonster.core.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GridFSStorage implements Storage {
    private GridFS gridFS;

    public GridFSStorage(GridFS gridFS) {
        this.gridFS = gridFS;
    }

    @Override
    public byte[] read(String key) throws FileNotFoundException {
        GridFSDBFile file = gridFS.findOne(key);
        if (file == null) {
            throw new FileNotFoundException();
        }

        try {
            return file.getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public void write(String key, byte[] content) {
        if (exists(key)) {
            try {
                delete(key);
            } catch (FileNotFoundException e) {

            }
        }

        GridFSInputFile file = gridFS.createFile(new ByteArrayInputStream(content), key);
        file.save();
    }

    @Override
    public boolean exists(String key) {
        GridFSDBFile file = gridFS.findOne(key);
        return (file != null);
    }

    @Override
    public void delete(String key) throws FileNotFoundException {
        GridFSDBFile file = gridFS.findOne(key);
        if (file == null) {
            throw new FileNotFoundException();
        }

        gridFS.remove(file);
    }

    @Override
    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        write(targetKey, read(sourceKey));

        delete(sourceKey);
    }
}
