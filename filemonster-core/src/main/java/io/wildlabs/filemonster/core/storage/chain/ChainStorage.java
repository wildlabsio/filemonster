package io.wildlabs.filemonster.core.storage.chain;

import io.wildlabs.filemonster.core.storage.Storage;
import java.io.FileNotFoundException;
import java.util.List;

public class ChainStorage implements Storage {

    private List<Storage> storages;

    public ChainStorage(List<Storage> storages) {
        this.storages = storages;
    }

    @Override
    public byte[] read(String key) throws FileNotFoundException {
        for (Storage storage : storages) {
            try {
                return storage.read(key);
            } catch (FileNotFoundException e) {

            }
        }

        throw new FileNotFoundException();
    }

    @Override
    public void write(String key, byte[] content) {
        for (Storage storage : storages) {
            storage.write(key, content);
        }
    }

    @Override
    public boolean exists(String key) {
        for (Storage storage : storages) {
            if (storage.exists(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void delete(String key) throws FileNotFoundException {
        if (!exists(key)) {
            throw new FileNotFoundException();
        }

        for (Storage storage : storages) {
            try {
                storage.delete(key);
            } catch (FileNotFoundException e) {

            }
        }
    }

    @Override
    public void rename(String sourceKey, String targetKey) throws FileNotFoundException {
        if (!exists(sourceKey)) {
            throw new FileNotFoundException();
        }

        for (Storage storage : storages) {
            try {
                storage.rename(sourceKey, targetKey);
            } catch (FileNotFoundException e) {

            }
        }
    }
}
