package io.wildlabs.filemonster.storage.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import io.wildlabs.filemonster.core.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DropboxStorage implements Storage {
    private DbxClientV2 dropboxClient;

    public DropboxStorage(DbxClientV2 dropboxClient) {
        this.dropboxClient = dropboxClient;
    }

    @Override
    public byte[] read(String key) throws FileNotFoundException {
        try {
            return dropboxClient.files().download(key).getInputStream().readAllBytes();
        } catch (DbxException | IOException e) {
            e.printStackTrace();
        }

        throw new FileNotFoundException();
    }

    @Override
    public void write(String key, byte[] content) {
        try {
            dropboxClient
                    .files()
                    .uploadBuilder(key)
                    .uploadAndFinish(new ByteArrayInputStream(content));
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(String key) {
        try {
            read(key);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    @Override
    public void delete(String key) throws FileNotFoundException {
        if (!exists(key)) {
            throw new FileNotFoundException();
        }

        try {
            dropboxClient.files().delete(key);
        } catch (DbxException e) {
            e.printStackTrace();
            throw new FileNotFoundException();
        }
    }

    @Override
    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        try {
            dropboxClient.files().move(sourceKey, targetKey);
        } catch (DbxException e) {
            e.printStackTrace();
            throw new FileNotFoundException();
        }
    }
}
